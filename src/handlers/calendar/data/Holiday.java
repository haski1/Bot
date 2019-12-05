package handlers.calendar.data;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.regex.Pattern;

public class Holiday
{
    private String name;
    private SimpleDate date;
    private static final Pattern patternHoliday = Pattern.compile("([0-3][0-9])\\.([0-1][0-9])\\s+(.*)");

    public Holiday(SimpleDate date)
    {
        this.date = date;
    }

    public Holiday(SimpleDate date, String name)
    {
        this.date = date;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public SimpleDate getDate()
    {
        return  date;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s", name, date);
    }

    public static Holiday parse(String line)
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
}