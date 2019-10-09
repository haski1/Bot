package handlers.quiz.instruction;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;
import handlers.quiz.data.QuizData;

public class Check implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var trueAnswer = ((QuizData)user.getData(State.Quiz)).answer;
        if (msg.getText().toLowerCase().equals(trueAnswer.toLowerCase()))
        {
            var result = "Верно";
            parent.out(new Answer(msg.getId(), result));
            parent.in(new Message(msg.getId(), "question"));
        }
        else
        {
            var result = "Не верно";
            parent.out(new Answer(msg.getId(), result));
        }
    }
}
