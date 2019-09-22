package handlers.basic;

import core.IIO;
import core.data.Message;
import core.data.Source;
import core.instruction.IInstruction;
import core.set.ISet;
import handlers.basic.handlers.HandlersSet;
import handlers.basic.instructions.InstructionsSet;
import platforms.PlatformsSet;
import platforms.terminal.TerminalIO;

public class BasicHandler implements IIO
{
    private ISet instructions;
    private ISet handlers;
    private ISet platforms;
    private Users users;

    public BasicHandler()
    {
        platforms = new PlatformsSet(this);
        handlers = new HandlersSet(this);
        instructions = new InstructionsSet();
        users = new Users();
    }

    @Override
    public void in(Message msg)
    {
        if (users.contains(msg.user))
        {
            msg.user = users.find(msg.user);
        }
        else
        {
            users.register(msg.user);
        }
        if (handlers.contains(msg.user.state))
        {
            ((IIO)handlers.find(msg.user.state)).in(msg);
        }
        else
        {
            var instruction = (IInstruction)instructions.find(msg.command);
            if (instruction != null)
            {
                instruction.execute(msg, this);
            }
        }
    }

    @Override
    public void out(Message msg)
    {
        var platform = (IIO)platforms.find(msg.user.platform);
        platform.out(msg);
    }

    public void run()
    {
        ((TerminalIO)platforms.find(Source.Terminal)).run();
    }
}
