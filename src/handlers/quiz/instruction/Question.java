package handlers.quiz.instruction;

import core.data.Message;
import core.instruction.BaseInstruction;
import handlers.quiz.QuizData;

public class Question extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = ((QuizData)msg.user.data.get("quiz")).question;
        msg.done = true;
    }
}
