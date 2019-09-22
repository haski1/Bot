package handlers.quiz.instruction;

import core.IIO;
import core.data.Message;
import core.instruction.BaseInstruction;
import handlers.quiz.data.QuizData;

public class Check extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler) {
        var trueAnswer = ((QuizData)msg.user.data.get("quiz")).answer;
        if (msg.text.equals(trueAnswer))
        {
            msg.result = "Верно";
        }
        else
        {
            msg.result = "Не верно";
        }
        msg.done = true;
        handler.out(msg);
    }
}
