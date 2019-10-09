package handlers.quiz.instruction;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;

public class StartQuiz implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Вы вошли в викторину!\nВыход: /exit";
        user.setState(State.Quiz);
        parent.out(new Answer(msg.getId(), result));
        parent.in(new Message(msg.getId(), "question"));
    }
}
