package handlers.basic.instructions;

import core.IIO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class Start extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler)
    {
        msg.result = "Привет! \nЭто iwanttoseebot\nПиши /startquiz чтобы проиграть в викторину!\n" +
                     "Пиши /startchat зайти в анонимный чат!";
        msg.done = true;
        handler.out(msg);
    }
}
