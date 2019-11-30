package handlers.calendar.instructions;

import core.command.BaseCommandsSet;
import handlers.calendar.data.BaseHoliday;

public class CalendarInstructionsSet extends BaseCommandsSet
{
    public CalendarInstructionsSet(BaseHoliday holidays)
    {
        super(new SetHoliday(holidays));
        register(new CheckHoliday(holidays));
        register(new Exit());

    }
}
