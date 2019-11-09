package handlers.chat;

import core.IO;
import core.data.Module;
import core.data.*;
import handlers.chat.instructions.ChatInstructionSet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Chat implements IO
{
    private ChatInstructionSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;
    private ConcurrentLinkedQueue<User> searchingUsers;

    public Chat(IO handler, ConcurrentHashMap<ID, User> users)
    {
        searchingUsers = new ConcurrentLinkedQueue<>();
        instructions = new ChatInstructionSet(searchingUsers);
        this.parentHandler = handler;
        this.users = users;
    }

    @Override
    public void in(Message msg)
    {
        var user = users.get(msg.getId());
        if (instructions.containsKey(msg.getCommand()))
        {
            instructions.get(msg.getCommand()).execute(msg, user, this);
        }
        else
        {
            if (msg.getCommand() == null)
            {
                instructions.getDefault().execute(msg, user, this);
            }
            else
            {
                out(new Answer(msg.getId(), "Неизвестная команда"));
            }

        }
    }

    @Override
    public void out(Answer ans)
    {
        parentHandler.out(ans);
    }

    public void connect(User first, User second)
    {
        first.setData(core.data.Module.Chat, second);
        second.setData(core.data.Module.Chat, first);
        var result = "Собеседник найден!\n(Предепреджение: вы общаетесь с реальным человеком!)";

        var ansToFirst = new Answer(first.getId(), result);
        var ansToSecond = new Answer(second.getId(), result);
        out(ansToFirst);
        out(ansToSecond);
    }

    public void disconnect(User user)
    {
        var objUser = user.getData();
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            userTwo.setData(core.data.Module.Chat, null);
            user.setData(Module.Chat, null);

            var result = "Собеседник вышел\n Для поиска нового собеседника напишите команду /search";
            var answer = new Answer(userTwo.getId(), result);
            answer.getButtons().add(Commands.Search.getCode());
            answer.getButtons().add(Commands.Exit.getCode());
            out(answer);
        }
    }
}
