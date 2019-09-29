package handlers.chat;

import core.IO;
import core.data.Message;
import handlers.chat.instructions.ChatInstructionSet;

public class Chat implements IO
{
    private ChatInstructionSet instructions;
    private IO parentHandler;

    public Chat(IO handler)
    {
        instructions = new ChatInstructionSet();
        this.parentHandler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = instructions.get(msg.command);
        if (instruction == null && msg.text != null)
        {
            instruction = instructions.getDefault();
        }
        instruction.execute(msg, this);
    }

    @Override
    public void out(Message msg)
    {
        parentHandler.out(msg);
    }
}
