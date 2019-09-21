package handlers.quiz.instruction;

import core.data.Message;
import core.instruction.BaseInstruction;

public class Start extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = "Викторина началась";
        msg.user.state = "quiz";
        msg.done = true;
    }
}
