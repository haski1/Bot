package handlers.basic.instructions;

import core.data.Message;
import core.instruction.BaseInstruction;

public class Start extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = "Привет! \nЭто iwanttoseebot\nПиши /startquiz чтобы проиграть в викторину!";
        msg.done = true;
    }
}
