package scripts.basic.instructions;

import core.Data.Source;
import core.Instruction.IInstruction;
import core.Instruction.IInstructionSet;

import java.util.Collection;
import java.util.HashMap;

public class InstructionsSet implements IInstructionSet
{
    private HashMap<String, IInstruction> instructions;

    public InstructionsSet()
    {
        instructions = new HashMap<String, IInstruction>();

        register(new Help());
    }

    @Override
    public IInstruction find(String name)
    {
        if (contains(name))
            return instructions.get(name);
        return null;
    }

    @Override
    public Collection<IInstruction> getAll()
    {
        return instructions.values();
    }

    @Override
    public void register(IInstruction instruction)
    {
        instructions.put(instruction.getName(), instruction);
    }

    public boolean contains(String name)
    {
        return instructions.containsKey(name);
    }
}
