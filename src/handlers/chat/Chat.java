package handlers.chat;

import core.IO;
import core.data.Message;
import core.instruction.Instruction;
import core.set.Set;
import handlers.chat.instructions.ChatInstructionSet;

public class Chat implements IO
{
    private Set instructions;
    private IO handler;

    public Chat(IO handler)
    {
        instructions = new ChatInstructionSet();
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = (Instruction)instructions.find(msg.command);
        if (instruction == null && msg.text != null)
        {
            instruction = (Instruction)instructions.find("dialog");
        }
        instruction.execute(msg, this);
    }

    @Override
    public void out(Message msg)
    {
        handler.out(msg);
    }
}
