package handlers.quiz.instruction;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;
import handlers.quiz.data.QuizData;

public class Check implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var trueAnswer = ((QuizData)user.getData(Module.Quiz)).answer;
        String result;
        if (msg.getText().toLowerCase().equals(trueAnswer.toLowerCase()))
        {
            result = "Верно";
            parent.out(new Answer(msg.getId(), result));
            parent.in(new Message(msg.getId(), "question"));
        }
        else
        {
            result = "Не верно";
            parent.out(new Answer(msg.getId(), result));
        }
    }
}
