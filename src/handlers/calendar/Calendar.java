package handlers.calendar;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.User;
import handlers.calendar.data.CalendarData;
import handlers.calendar.instructions.CalendarInstructionsSet;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calendar implements IO
{
    private CalendarInstructionsSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;

    public Calendar(IO handler, ConcurrentHashMap<ID, User> users) throws Exception {
        var holidays = getHolidays();
        instructions = new CalendarInstructionsSet(holidays);
        this.parentHandler = handler;
        this.users = users;
    }

    private ArrayList<String> getHolidays() throws Exception
    {
        var holidays = new ArrayList<String>();
        var urlAPI = new URL("http://kakoysegodnyaprazdnik.ru/");
        var yc = urlAPI.openConnection();
        var in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        Pattern pattern = Pattern.compile("\"text\">(.*?)</span>");
        while ((inputLine = in.readLine()) != null)
        {
            Matcher matcher = pattern.matcher(inputLine);
            if (matcher.find())
            {
                var splitText = inputLine.split("\"text\"");
                for (var i = 1; i < splitText.length; i++)
                {
                    var holiday = splitText[i].split("<")[0].substring(1);
                    holidays.add(holiday);
                }
            }
        }
        in.close();
        return holidays;
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
            System.out.println(msg.getCommand());
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
}
