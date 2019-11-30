package handlers.calendar;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.BaseHoliday;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;
import handlers.calendar.instructions.CalendarInstructionsSet;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar implements IO
{
    private CalendarInstructionsSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;
    private BaseHoliday holidays;

    public Calendar(IO handler, ConcurrentHashMap<ID, User> users) throws Exception
    {
        this.holidays = getHolidays();
        instructions = new CalendarInstructionsSet(holidays);
        this.parentHandler = handler;
        this.users = users;

        Runnable task = this::run;
        Thread thread = new Thread(task);
        thread.start();
    }

    private BaseHoliday getHolidays() throws Exception
    {
        Holiday holiday = new Holiday("Выходной", new SimpleDate(30, 11));
        ArrayList<Holiday> holidays = new ArrayList<>();
        holidays.add(holiday);
        BaseHoliday baseHolidays = new BaseHoliday(holidays);
        return baseHolidays;
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
        SimpleDate simpleDate = new SimpleDate(LocalDate.now().getDayOfMonth(),
                                               LocalDate.now().getMonth().getValue());
        var users = holidays.getUsersWhoHasReminder();
        for (var user: users)
        {
            var reminder = holidays.getUserReminder(user, simpleDate);
            if (reminder != null)
            {
                var result = reminder.getName();
                out(new Answer(user, result));
            }
        }
    }

    public void run()
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
