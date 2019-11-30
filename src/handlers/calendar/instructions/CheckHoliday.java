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

import java.time.LocalDate;

public class CheckHoliday implements Command
{
    private BaseHoliday holidays;

    public CheckHoliday(BaseHoliday holidays)
    {
        this.holidays = holidays;
    }

    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.CheckHoliday;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        SimpleDate simpleDate = new SimpleDate(LocalDate.now().getDayOfMonth(),
                                               LocalDate.now().getMonth().getValue());
        StringBuilder result = new StringBuilder();
        handlers.calendar.data.Holiday holiday = holidays.getHoliday(simpleDate);
        if (holiday != null)
        {
            result.append(holiday.getDate());
            result.append('\n');
            result.append(holiday.getName());
            result.append('\n');
        }
        handlers.calendar.data.Holiday userHoliday = holidays.getUserHoliday(user.getId(), simpleDate);
        if (userHoliday != null)
        {
            result.append(userHoliday.getName());
            result.append('\n');
        }
        parent.out(new Answer(msg.getId(), result.toString()));
    }
}
