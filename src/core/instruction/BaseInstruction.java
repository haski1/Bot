package core.instruction;

import core.IIO;
import core.data.Message;
import core.instruction.IInstruction;

public abstract class BaseInstruction implements IInstruction
{
    @Override
    public String getName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public abstract void execute(Message msg);
}
