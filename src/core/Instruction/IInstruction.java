package core.Instruction;

import core.Data.Message;

public interface IInstruction
{
    public String getName();
    public void execute(Message msg);
}
