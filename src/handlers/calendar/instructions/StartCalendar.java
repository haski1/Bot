package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.*;
import core.data.Module;

public class StartCalendar implements Command
{
    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.Calendar;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Вы вошли в календарь!\nСегодняшний праздник /checkholiday\n Установить праздник holiday dd.MM Name\nУстановить напоминание reminder dd.MM Name\nВыход: /exit";
        user.setModule(Module.Calendar);
        var answer = new Answer(user.getId(), result);
        answer.getButtons().add(CommandInfo.CheckHoliday.getEmoji());
        answer.getButtons().add(CommandInfo.Exit.getEmoji());
        parent.out(answer);
    }
}
