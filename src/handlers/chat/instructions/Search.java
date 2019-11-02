package handlers.chat.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.Commands;
import core.data.Message;
import core.data.User;
import handlers.chat.Chat;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Search implements Command
{
    private ConcurrentLinkedQueue<User> searchingUsers;

    Search(ConcurrentLinkedQueue<User> searchingUsers)
    {
        this.searchingUsers = searchingUsers;
    }

    @Override
    public Commands getName()
    {
        return Commands.Search;
    }

    @Override
    public void execute(Message msg, User user, IO chat)
    {
        if (user.getData() != null)
        {
            ((Chat)chat).disconnect(user);
        }
        if (searchingUsers.contains(user))
        {
            return;
        }
        searchingUsers.add(user);
        var result = "Ищем собеседника...";
        var answer = new Answer(user.getId(), result);
        answer.getButtons().add(Commands.Exit.getCode());
        chat.out(answer);


        if (searchingUsers.size() >= 2)
        {
            var first = searchingUsers.poll();
            var second = searchingUsers.poll();
            ((Chat)chat).connect(first, second);
        }
    }
}
