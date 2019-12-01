package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.BaseHoliday;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;
import handlers.calendar.data.Step;

import java.util.regex.Pattern;

public class SetEvent implements Command
{

    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.SetEvent;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        StringBuilder result = new StringBuilder();
        user.setData(Step.SetHolidayDate);
        result.append("Напишите дату\n");
        result.append("Формат дд.мм\n");
        result.append("Пример 01.01\n");
        var answer = new Answer(user.getId(), result.toString());
        answer.getButtons().add(CommandInfo.Exit.getEmoji());
        parent.out(answer);
    }
}
