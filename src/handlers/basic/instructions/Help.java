package handlers.basic.instructions;

import core.IIO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class Help extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler)
    {
        msg.result = "Наберите команду /start чтобы узнать больше!";
        msg.done = true;
        handler.out(msg);
    }
}
