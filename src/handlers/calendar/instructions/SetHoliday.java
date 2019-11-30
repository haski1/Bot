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

import java.util.regex.Pattern;

public class SetHoliday implements Command
{
    private BaseHoliday holidays;
    private Pattern patternHoliday = Pattern.compile("holyday\\s+([0-3]?[0-9])\\.([0-1][0-9])\\s+(.*)");
    private Pattern patternReminder = Pattern.compile("reminder\\s+([0-3]?[0-9])\\.([0-1][0-9])\\s+(.*)");

    public SetHoliday(BaseHoliday holidays)
    {
        this.holidays = holidays;
    }

    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.SetHoliday;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var match = patternHoliday.matcher(msg.getText());
        StringBuilder result = new StringBuilder();
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            String name = match.group(3);
            holidays.setUserHoliday(user.getId(), new Holiday(name, new SimpleDate(day, month)));
            result.append("Holiday added");
        }
        match = patternReminder.matcher(msg.getText());
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            String name = match.group(3);
            holidays.setUserReminder(user.getId(), new Holiday(name, new SimpleDate(day, month)));
            result.append("Reminder added");
        }
        if (result.length() == 0)
        {
            result.append("Чтобы установить праздник напиши holiday dd.MM Name\n");
            result.append("Например: holiday 31.12 Новый год!\n");
            result.append("Чтобы установить напоминание напиши reminder dd.MM Name\n");
            result.append("Например: reminder 14.02 Контрольная!\n");
        }
        parent.out(new Answer(msg.getId(), result.toString()));
    }
}
