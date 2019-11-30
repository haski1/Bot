package handlers.calendar.data;

import java.util.Date;

public class Holiday
{
    private String name;
    private SimpleDate date;

    public Holiday(String name, SimpleDate date)
    {
        this.name = name;
        this.date = date;
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