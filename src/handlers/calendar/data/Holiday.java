package handlers.calendar.data;

import java.util.Date;

public class Holiday
{
    private String name;
    private SimpleDate date;

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
}