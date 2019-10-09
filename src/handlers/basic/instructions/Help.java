package handlers.basic.instructions;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.Source;
import core.data.User;
import core.command.Command;

public class Help implements Command
{
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Наберите команду /start чтобы узнать больше!";
        var answer = new Answer(msg.getId(), result);
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("\uD83D\uDE80");
        }
        parent.out(answer);
    }
}
