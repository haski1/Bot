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
import handlers.quiz.data.QuizData;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar implements IO
{
    private CalendarInstructionsSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;
    private BaseHoliday holidays;
    private Pattern patternHoliday = Pattern.compile("([0-3][0-9])\\.([0-1][0-9])\\s+(.*)");

    public Calendar(IO handler, ConcurrentHashMap<ID, User> users, Path holidaysPath) throws IOException
    {
        this.holidays = getHolidays(holidaysPath);
        instructions = new CalendarInstructionsSet(holidays);
        this.parentHandler = handler;
        this.users = users;

        Runnable task = this::run;
        Thread thread = new Thread(task);
        thread.start();
    }

    private BaseHoliday getHolidays(Path path) throws IOException
    {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Holiday> holidays = new ArrayList<>();
        for(String line: lines)
        {
            holidays.add(parseLine(line));
        }
        return new BaseHoliday(holidays);
    }

    private Holiday parseLine(String line)
    {
        var match = patternHoliday.matcher(line);
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            String name = match.group(3);
            return new Holiday(new SimpleDate(day, month), name);
        }
        return null;
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
