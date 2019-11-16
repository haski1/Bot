package handlers.calendar.instructions;

import core.command.BaseCommandsSet;
import handlers.calendar.data.CalendarData;

import java.util.ArrayList;
import java.util.List;

public class CalendarInstructionsSet extends BaseCommandsSet
{
    public CalendarInstructionsSet(ArrayList<String> holidays)
    {
        super(new StartCalendar());
        register(new Exit());
        register(new Search(holidays));
    }
}
