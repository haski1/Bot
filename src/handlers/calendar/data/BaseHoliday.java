package handlers.calendar.data;

import core.data.ID;
import core.data.User;
import javassist.compiler.ast.Pair;

import java.util.*;

public class BaseHoliday
{
    private Map<SimpleDate, Holiday> basicHolidays = new HashMap<>();
    private Map<ID, Map<SimpleDate, Holiday>> usersHolidays = new HashMap<>();
    private Map<ID, Map<SimpleDate, Holiday>> usersReminders = new HashMap<>();

    public BaseHoliday(List<Holiday> holidays)
    {
        for (Holiday holiday: holidays)
        {
            basicHolidays.put(holiday.getDate(), holiday);
        }
    }

    public Holiday getHoliday(SimpleDate date)
    {
        return basicHolidays.get(date);
    }

    public Holiday getUserHoliday(ID id, SimpleDate date)
    {
        return usersHolidays.get(id).get(date);
    }

    public Holiday getUserReminder(ID id, SimpleDate date)
    {
        return usersReminders.get(id).get(date);
    }

    public Set<ID> getUsersWhoHasReminder()
    {
        return usersReminders.keySet();
    }

    public void setUserHoliday(ID id, Holiday holiday)
    {
        if (!usersHolidays.containsKey(id))
        {
            usersHolidays.put(id, new HashMap<>());
        }
        usersHolidays.get(id).put(holiday.getDate(), holiday);
    }

    public void setUserReminder(ID id, Holiday holiday)
    {
        if (!usersReminders.containsKey(id))
        {
            usersReminders.put(id, new HashMap<>());
        }
        usersReminders.get(id).put(holiday.getDate(), holiday);
    }
}