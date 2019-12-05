package handlers.calendar.instructions;

import core.command.BaseCommandsSet;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;
import handlers.calendar.context.handlers.*;
import handlers.calendar.data.DataBaseHolidays;

public class CalendarInstructionsSet extends BaseCommandsSet
{
    public CalendarInstructionsSet(DataBaseHolidays holidays)
    {
        super(new HandleContext());

        ContextHandler base = new BaseContextHandler();
        ContextHandler setReminderDate = new SetReminderDate(base, holidays);
        ContextHandler setReminder = new SetReminder(setReminderDate, base, holidays);
        ContextHandler setHolidayName = new SetHolidayName(setReminder);
        ContextHandler setHolidayDate = new SetHolidayDate(setHolidayName);

        register(new SetEvent(setHolidayDate));
        register(new CheckHoliday(holidays));
        register(new Exit());

    }
}
