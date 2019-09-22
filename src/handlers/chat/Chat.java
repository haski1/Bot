package handlers.chat;

import core.IIO;
import core.data.Message;
import core.instruction.IInstruction;
import core.set.ISet;
import handlers.chat.instructions.ChatInstructionSet;

public class Chat implements IIO
{
    private ISet instructions;
    private IIO handler;

    public Chat(IIO handler)
    {
        instructions = new ChatInstructionSet();
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = (IInstruction)instructions.find(msg.command);
        if (instruction == null && msg.text != null)
        {
            instruction = (IInstruction)instructions.find("dialog");
        }
        instruction.execute(msg, this);
    }

    @Override
    public void out(Message msg)
    {
        handler.out(msg);
    }
}
