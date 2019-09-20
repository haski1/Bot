package handlers.basic.instructions;

import core.data.Message;
import core.instruction.BaseInstruction;

public class Help extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = "Набери команду /list для просмотра списка всех сценариев работы \n" +
                "Набери команду /start и сценарий чтобы запустить его";
        msg.done = true;
    }
}
