package handlers.quiz.instruction;

import core.IO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;
import handlers.quiz.data.QuizData;

public class Check extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler) {
        var trueAnswer = ((QuizData)msg.user.data.get(State.Quiz)).answer;
        if (msg.text.toLowerCase().equals(trueAnswer.toLowerCase()))
        {
            msg.result = "Верно";
            handler.out(msg);
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
