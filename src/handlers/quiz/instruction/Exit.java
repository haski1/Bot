package handlers.quiz.instruction;

import core.IO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;

public class Exit extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.user.state = State.BasicHandler;
        msg.done = true;
    }
}
