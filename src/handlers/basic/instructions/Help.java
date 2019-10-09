package handlers.basic.instructions;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.User;
import core.command.Command;

public class Help implements Command
{
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Наберите команду /start чтобы узнать больше!";
        parent.out(new Answer(msg.getId(), result));
    }
}
