package handlers.calendar.data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class SimpleDate
{
    private int day;
    private int month;
    private static Pattern patternDate = Pattern.compile("^([0-3]?[0-9])\\.([0-1][0-9])$");

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

    public static SimpleDate fromLocalDate(LocalDate localDate)
    {
        return new SimpleDate(LocalDate.now().getDayOfMonth(),
                              LocalDate.now().getMonth().getValue());
    }

    public static SimpleDate parse(String line)
    {
        var match = patternDate.matcher(line);
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            return new SimpleDate(day, month);
        }
        return null;
    }
}
