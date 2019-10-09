package handlers.quiz.instruction;

import core.IO;
import core.data.*;
import core.command.Command;

public class StartQuiz implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Вы вошли в викторину!\nВыход: /exit";
        user.setState(State.Quiz);
        var answer = new Answer(user.getId(), result);
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("⛔");
        }
        parent.out(answer);
        parent.in(new Message(msg.getId(), "question"));
    }
}
