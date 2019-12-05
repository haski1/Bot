package handlers.calendar.data;

import core.data.ID;

import java.util.*;

public class DataBaseHolidays
{
    private Map<SimpleDate, List<Holiday>> basicHolidays = new HashMap<>();
    private Map<ID, Map<SimpleDate, List<Holiday>>> usersHolidays = new HashMap<>();
    private Map<ID, Map<SimpleDate, List<Holiday>>> usersReminders = new HashMap<>();

    public DataBaseHolidays(List<Holiday> holidays)
    {
        for (Holiday holiday: holidays)
        {
            basicHolidays.put(holiday.getDate(), Arrays.asList(holiday));
        }
    }

    public List<Holiday> getUserHolidays(ID id, SimpleDate date)
    {
        List<Holiday> holidays = new ArrayList<>();
        if (usersHolidays.containsKey(id) && usersHolidays.get(id).containsKey(date))
            holidays.addAll(usersHolidays.get(id).get(date));
        if (basicHolidays.containsKey(date))
            holidays.addAll(basicHolidays.get(date));
        return holidays;
    }

    public Map<ID, List<Holiday>> getUsersReminders(SimpleDate date)
    {
        Map<ID, List<Holiday>> reminders = new HashMap<>();
        for (var entry:usersReminders.entrySet())
            if (entry.getValue().containsKey(date))
                reminders.put(entry.getKey(), entry.getValue().get(date));
        return reminders;
    }

    public void setUserHoliday(ID id, Holiday holiday)
    {
        addItemToCollection(usersHolidays, id, holiday.getDate(), holiday);
    }

    public void setUserReminder(ID id, SimpleDate date, Holiday holiday)
    {
        addItemToCollection(usersReminders, id, date, holiday);
    }

    private void addItemToCollection(Map<ID, Map<SimpleDate, List<Holiday>>> collection, ID id, SimpleDate date, Holiday holiday)
    {
        if (!collection.containsKey(id))
        {
            collection.put(id, new HashMap<>());
        }
        if (!collection.get(id).containsKey(date))
        {
            collection.get(id).put(date, new ArrayList<>());
        }
        collection.get(id).get(date).add(holiday);
    }
}