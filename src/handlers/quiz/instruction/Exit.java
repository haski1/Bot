package handlers.quiz.instruction;

import core.data.Message;
import core.instruction.BaseInstruction;

public class Exit extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.user.state = "basichandler";
        msg.done = true;
    }
}
