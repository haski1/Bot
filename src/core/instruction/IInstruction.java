package core.instruction;

import core.IIO;
import core.data.Message;

public interface IInstruction
{
    public String getName();
    public void execute(Message msg);
}
