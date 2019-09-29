package handlers.basic;

import core.IO;
import core.data.Message;
import core.data.Source;
import core.instruction.Instruction;
import core.set.Set;
import handlers.basic.handlers.HandlersSet;
import handlers.basic.instructions.InstructionsSet;
import platforms.PlatformsSet;
import platforms.terminal.TerminalIO;

public class BasicHandler implements IO
{
    private Set instructions;
    private Set handlers;
    private Set platforms;
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
            ((IO)handlers.find(msg.user.state)).in(msg);
        }
        else
        {
            var instruction = (Instruction)instructions.find(msg.command);
            if (instruction != null)
            {
                instruction.execute(msg, this);
            }
        }
    }

    @Override
    public void out(Message msg)
    {
        var platform = (IO)platforms.find(msg.user.platform);
        platform.out(msg);
    }

    public void run()
    {
        ((TerminalIO)platforms.find(Source.Terminal)).run();
    }
}
