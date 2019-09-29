package handlers.chat.instructions;

import core.instruction.Instruction;
import core.set.BaseSet;

public class ChatInstructionSet extends BaseSet<String, Instruction>
{
    public ChatInstructionSet()
    {
        super();
        register(new Search());
        register(new Dialog());
        register(new Exit());
    }

    public void register(Instruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
