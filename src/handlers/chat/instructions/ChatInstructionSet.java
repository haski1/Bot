package handlers.chat.instructions;

import core.instruction.IInstruction;
import core.set.BaseSet;

public class ChatInstructionSet extends BaseSet<String, IInstruction>
{
    public ChatInstructionSet()
    {
        super();
        register(new Search());
        register(new Dialog());
        register(new Exit());
    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
