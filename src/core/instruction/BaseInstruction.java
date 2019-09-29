package core.instruction;

import core.IO;
import core.data.Message;

public abstract class BaseInstruction implements Instruction
{
    @Override
    public String getName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public abstract void execute(Message msg, IO handler);
}
