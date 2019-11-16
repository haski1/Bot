package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.Commands;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.CalendarData;

import java.util.ArrayList;
import java.util.List;

public class Search implements Command
{
    private ArrayList<String> holidays;

    public Search(ArrayList<String> holidays)
    {
        this.holidays = holidays;
    }

    @Override
    public Commands getName()
    {
        return Commands.Search;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var result = new StringBuilder();
        result.append("Сегодняшние праздники:\n");
        for (var holiday: holidays)
        {
            result.append("⚫");
            result.append(holiday);
            result.append("\n");
        }
        parent.out(new Answer(msg.getId(), result.toString()));
    }
}
