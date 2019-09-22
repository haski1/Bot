package handlers.quiz.instruction;

import core.IIO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class Exit extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler)
    {
        msg.user.state = "basichandler";
        msg.done = true;
    }
}
