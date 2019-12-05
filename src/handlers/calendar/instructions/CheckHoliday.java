package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.DataBaseHolidays;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;

import java.time.LocalDate;
import java.util.List;

public class CheckHoliday implements Command
{
    private DataBaseHolidays dataBaseHolidays;

    public CheckHoliday(DataBaseHolidays holidays)
    {
        this.dataBaseHolidays = holidays;
    }

    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.CheckHoliday;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        SimpleDate simpleDate = SimpleDate.fromLocalDate(LocalDate.now());
        StringBuilder result = new StringBuilder();
        List<Holiday> holidays = dataBaseHolidays.getUserHolidays(user.getId(), simpleDate);

        for (Holiday holiday: holidays)
            result.append(holiday).append("\n");

        if (result.length() == 0)
            result.append("Сегодня без праздников");

        parent.out(new Answer(msg.getId(), result.toString()));
    }
}
