package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.*;
import core.data.Module;

public class StartCalendar implements Command
{
    @Override
    public Commands getName()
    {
        return Commands.Calendar;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Вы вошли в календарь!\nДля поиска праздников напиши команду /search\nВыход: /exit";
        user.setModule(Module.Calendar);
        var answer = new Answer(user.getId(), result);
        answer.getButtons().add(Commands.Search.getCode());
        answer.getButtons().add(Commands.Exit.getCode());
        parent.out(answer);
    }
}
