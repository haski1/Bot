package scripts.basic.instructions;

import core.Instruction.IInstruction;
import core.Data.Message;

public class Help implements IInstruction
{
    public Help()
    {
    }

    @Override
    public String getName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public void execute(Message msg)
    {
        msg.result = "Bot";
        msg.done = true;
    }
}
