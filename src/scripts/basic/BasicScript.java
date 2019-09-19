package scripts.basic;

import core.Data.Message;
import core.IIO;
import core.Instruction.IInstructionSet;
import scripts.basic.instructions.InstructionsSet;

public class BasicScript implements IIO
{
    private IInstructionSet instructionSet;
    private IIO handler;

    public BasicScript(IIO handler)
    {
        instructionSet = new InstructionsSet();
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = instructionSet.find(msg.instruction);
        if (instruction != null) instruction.execute(msg);

        if (msg.done)
            out(msg);
    }

    @Override
    public void out(Message msg)
    {
        handler.out(msg);
    }
}
