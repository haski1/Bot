package handlers.basic.instructions;

import core.IO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class Help extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.result = "Наберите команду /start чтобы узнать больше!";
        msg.done = true;
        handler.out(msg);
    }
}
