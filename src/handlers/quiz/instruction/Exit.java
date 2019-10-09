package handlers.quiz.instruction;

import core.IO;
import core.data.*;
import core.command.Command;

public class Exit implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setState(State.Basic);
        var answer = new Answer(msg.getId(), "Вы вышли из викторины");
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("\uD83D\uDE80");
        }
        parent.out(answer);
    }
}
