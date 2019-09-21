package handlers.quiz.instruction;

import core.data.Message;
import core.instruction.BaseInstruction;
import handlers.quiz.QuizData;

import java.util.HashMap;

public class Check extends BaseInstruction
{
    @Override
    public void execute(Message msg) {
        var trueAnswer = ((QuizData)msg.user.data.get("quiz")).answer;
        if (msg.instruction.equals(trueAnswer))
        {
            msg.result = "Верно";
        }
        else
        {
            msg.result = "Не верно";
        }
        msg.done = true;
    }
}
