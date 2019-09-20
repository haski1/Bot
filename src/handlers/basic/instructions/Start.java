package handlers.basic.instructions;

import core.data.Message;
import core.instruction.BaseInstruction;

public class Start extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = "Привет! \nЭто iwanttoseebot\nПока я ничего не умею";
        msg.done = true;
    }
}
