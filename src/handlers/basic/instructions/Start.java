package handlers.basic.instructions;

import core.IO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class Start extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.result = "Привет! \nЭто iwanttoseebot\nПиши /startquiz чтобы проиграть в викторину!\n" +
                     "Пиши /startchat зайти в анонимный чат!";
        msg.done = true;
        handler.out(msg);
    }
}
