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
        if (msg.text.toLowerCase().equals(trueAnswer))
        {
            msg.result = "Верно";
            msg.command = "question";
            handler.in(msg);
        }
        else
        {
            msg.result = "Не верно";
            handler.out(msg);
        }
        msg.done = true;
    }
}
