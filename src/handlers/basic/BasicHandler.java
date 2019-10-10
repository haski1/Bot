package handlers.basic;

import core.IO;
import core.data.*;
import handlers.basic.handlers.HandlersSet;
import handlers.basic.instructions.InstructionsSet;
import platforms.PlatformsSet;
import platforms.terminal.TerminalIO;

import java.util.HashMap;

public class BasicHandler implements IO
{
    private InstructionsSet instructions;
    private HandlersSet handlers;
    private PlatformsSet platforms;
    private HashMap<ID, User> users;

    public BasicHandler()
    {
        platforms = new PlatformsSet(this);
        users = new HashMap<>();
        handlers = new HandlersSet(this, users);
        instructions = new InstructionsSet();

    }

    @Override
    public void in(Message msg)
    {
        if (!(users.containsKey(msg.getId())))
        {
            users.put(msg.getId(), new User(msg.getId()));
        }
        var user = users.get(msg.getId());
        if (handlers.containsKey(user.getState()))
        {
            (handlers.get(user.getState())).in(msg);
        } else
        {
            if (instructions.containsKey(msg.getCommand()))
            {
                instructions.get(msg.getCommand()).execute(msg, user, this);
            } else
            {
                instructions.getDefault().execute(msg, user, this);
            }
        }
    }

    @Override
    public void out(Answer answer)
    {
        var platform = platforms.get(answer.getId().getPlatform());
        platform.out(answer);
    }

    public void run()
    {
        ((TerminalIO) platforms.get(Source.Terminal)).run();
    }
}
