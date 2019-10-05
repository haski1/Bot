package handlers.basic;

import core.IO;
import core.data.Message;
import core.data.Source;
import core.data.User;
import handlers.basic.handlers.HandlersSet;
import handlers.basic.instructions.InstructionsSet;
import platforms.PlatformsSet;
import platforms.terminal.TerminalIO;

import java.util.ArrayList;
import java.util.List;

public class BasicHandler implements IO
{
    private InstructionsSet instructions;
    private HandlersSet handlers;
    private PlatformsSet platforms;
    private List<User> users;

    public BasicHandler()
    {
        platforms = new PlatformsSet(this);
        handlers = new HandlersSet(this);
        instructions = new InstructionsSet();
        users = new ArrayList<>();
    }

    @Override
    public void in(Message msg)
    {
        if (users.contains(msg.user))
        {
            var index = users.indexOf(msg.user);
            msg.user = users.get(index);
        } else
        {
            users.add(msg.user);
        }
        if (handlers.containsKey(msg.user.state))
        {
            (handlers.get(msg.user.state)).in(msg);
        } else
        {
            var instruction = instructions.get(msg.command);
            if (instruction != null)
            {
                instruction.execute(msg, this);
            }
            else
            {
                instructions.getDefault().execute(msg, this);
            }
        }
    }

    @Override
    public void out(Message msg)
    {
        var platform = platforms.get(msg.user.platform);
        platform.out(msg);
    }

    public void run()
    {
        ((TerminalIO) platforms.get(Source.Terminal)).run();
    }
}
