package handlers.basic.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

public class Help implements Command
{
    public CommandInfo getInfo()
    {
        return CommandInfo.Help;
    }

    public void execute(Message msg, User user, IO parent)
    {
        var result = "Наберите команду /start чтобы узнать больше!";
        var answer = new Answer(msg.getId(), result);
        answer.getButtons().add(CommandInfo.Start.getEmoji());
        parent.out(answer);
    }
}
