package handlers.calendar.data;

import java.util.Date;
import java.util.Objects;

public class SimpleDate
{
    private int day;
    private int month;

    public SimpleDate(int day, int month)
    {
        this.day = day;
        this.month = month;
    }

    @Override
    public String toString()
    {
        return String.format("%s.%s", day, month);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDate date = (SimpleDate) o;
        return day == date.day &&
                month == date.month;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(day, month);
    }
}
