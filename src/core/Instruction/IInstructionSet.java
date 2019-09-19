package core.Instruction;

import core.Data.Source;

import java.util.Collection;

public interface IInstructionSet
{
    public void register(IInstruction instruction);
    public IInstruction find(String name);
    public Collection<IInstruction> getAll();
}
