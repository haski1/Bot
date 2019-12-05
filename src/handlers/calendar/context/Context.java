package handlers.calendar.context;

import handlers.calendar.context.handlers.SetHolidayDate;
import handlers.calendar.data.SimpleDate;

import java.util.Objects;

public class Context
{
    public SimpleDate date;
    public SimpleDate dateReminder;
    public String name;
    public ContextHandler handler;


    public Context(ContextHandler firstHandler)
    {
        handler = firstHandler;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(date, context.date) &&
                Objects.equals(name, context.name) &&
                Objects.equals(dateReminder, context.dateReminder);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(date, name, dateReminder);
    }
}
