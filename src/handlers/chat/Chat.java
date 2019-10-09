package handlers.chat;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.User;
import handlers.chat.instructions.ChatInstructionSet;

import java.util.HashMap;

public class Chat implements IO
{
    private ChatInstructionSet instructions;
    private HashMap<ID, User> users;
    private IO parentHandler;

    public Chat(IO handler, HashMap<ID, User> users)
    {
        instructions = new ChatInstructionSet();
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
}
