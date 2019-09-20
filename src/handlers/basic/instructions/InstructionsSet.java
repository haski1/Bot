package handlers.basic.instructions;

import core.set.BaseSet;
import core.instruction.IInstruction;

public class InstructionsSet extends BaseSet<String, IInstruction>
{
    public InstructionsSet()
    {
        super();
        register(new Start());
        //register(new Help());
        //register(new List());
    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
