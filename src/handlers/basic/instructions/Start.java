package handlers.basic.instructions;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.Source;
import core.data.User;
import core.command.Command;

public class Start implements Command
{
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Привет! \nЭто iwanttoseebot\nПиши /startquiz чтобы проиграть в викторину!\n" +
                "Пиши /startchat зайти в анонимный чат!";
        var answer = new Answer(msg.getId(), result);
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("\uD83E\uDDE0");
            answer.addButton("\uD83D\uDE48");
        }
        parent.out(answer);
    }
}