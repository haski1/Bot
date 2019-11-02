package handlers.quiz.instruction;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.Commands;
import core.data.Message;
import core.data.User;
import handlers.quiz.data.QuizData;

public class Check implements Command
{
    @Override
    public Commands getName() {
        return Commands.Check;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var trueAnswer = ((QuizData)user.getData()).answer;
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
