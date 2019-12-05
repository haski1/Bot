package handlers.calendar;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.DataBaseHolidays;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;
import handlers.calendar.instructions.CalendarInstructionsSet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar implements IO
{
    private CalendarInstructionsSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;
    private DataBaseHolidays dataBaseHolidays;


    public Calendar(IO handler, ConcurrentHashMap<ID, User> users, Path holidaysPath) throws IOException
    {
        this.dataBaseHolidays = getHolidays(holidaysPath);
        instructions = new CalendarInstructionsSet(dataBaseHolidays);
        this.parentHandler = handler;
        this.users = users;

        Runnable task = this::startCheckReminders;
        Thread thread = new Thread(task);
        thread.start();
    }

    private DataBaseHolidays getHolidays(Path path) throws IOException
    {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Holiday> holidays = new ArrayList<>();
        for(String line: lines)
        {
            holidays.add(Holiday.parse(line));
        }
        return new DataBaseHolidays(holidays);
    }


    @Override
    public void in(Message msg)
    {
        var user = users.get(msg.getId());
        if (instructions.containsKey(msg.getCommand()))
        {
            instructions.get(msg.getCommand()).execute(msg, user, this);
        }
        else
        {
            if (msg.getCommand() == null)
            {
                instructions.getDefault().execute(msg, user, this);
            }
            else
            {
                out(new Answer(msg.getId(), "Неизвестная команда"));
            }
        }
    }

    @Override
    public void out(Answer ans)
    {
        parentHandler.out(ans);
    }

    public void checkReminders()
    {
        SimpleDate simpleDate = SimpleDate.fromLocalDate(LocalDate.now());
        var usersReminders = dataBaseHolidays.getUsersReminders(simpleDate);
        for (var entry: usersReminders.entrySet())
        {
            StringBuilder result = new StringBuilder("Напоминания:\n");
            List<Holiday> reminders = entry.getValue();
            for (Holiday reminder:reminders)
            {
                result.append(reminder);
            }
            out(new Answer(entry.getKey(), result.toString()));
        }
    }

    public void startCheckReminders()
    {
        while (true)
        {
            try
            {
                Thread.sleep(60000);
            }
            catch (InterruptedException e)
            {
            }
            checkReminders();
        }
    }
}
