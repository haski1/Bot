package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.*;
import handlers.calendar.data.BaseHoliday;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;
import handlers.calendar.data.Step;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class HandleStep implements Command
{
    private BaseHoliday holidays;
    private HashMap<ID, Context> usersContext = new HashMap<>();
    private Pattern patternDate = Pattern.compile("^([0-3]?[0-9])\\.([0-1][0-9])$");

    public HandleStep(BaseHoliday holidays)
    {
        this.holidays = holidays;
    }
    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.HandleStep;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        Step step = (Step)user.getData();
        if (step == Step.SetHolidayDate)
        {
            setHolidayDate(msg, user, parent);
        }
        else if (step == Step.SetHolidayName)
        {
            setHolidayName(msg, user, parent);
        }
        else if (step == Step.SetReminder)
        {
            setReminder(msg, user, parent);
        }
        else if (step == Step.SetReminderDate)
        {
            setReminderDate(msg, user, parent);
        }
        else
        {
            parent.out(new Answer(msg.getId(), "Не понимаю\n"));
        }
    }

    private void setHolidayDate(Message msg, User user, IO parent)
    {
        var match = patternDate.matcher(msg.getText());
        StringBuilder result = new StringBuilder();
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            usersContext.put(user.getId(), new Context(new SimpleDate(day, month)));
            user.setData(Step.SetHolidayName);

            result.append("Напишите название\n");
        }
        else
        {
            result.append("Неправильные формат\n");
        }
        parent.out(new Answer(msg.getId(), result.toString()));
    }

    private void setHolidayName(Message msg, User user, IO parent)
    {
        usersContext.get(user.getId()).name = msg.getText();
        user.setData(Step.SetReminder);
        StringBuilder result = new StringBuilder();
        result.append("Добавить напоминание?\n");
        var answer = new Answer(user.getId(), result.toString());
        answer.getButtons().add(CommandInfo.Yes.getEmoji());
        answer.getButtons().add(CommandInfo.No.getEmoji());
        parent.out(answer);
    }

    private void setReminder(Message msg, User user, IO parent)
    {
        StringBuilder result = new StringBuilder();
        if ("да".equals(msg.getText().toLowerCase()))
        {
            user.setData(Step.SetReminderDate);
            result.append("Напишите дату\n");
            result.append("Формат дд.мм\n");
            result.append("Пример 01.01\n");
            var answer = new Answer(user.getId(), result.toString());
            answer.getButtons().add(CommandInfo.Exit.getEmoji());
            parent.out(answer);
            return;
        }
        if ("нет".equals(msg.getText().toLowerCase()))
        {
            Context userContext = usersContext.get(user.getId());
            holidays.setUserHoliday(user.getId(), new Holiday(userContext.date, userContext.name));
            result.append("Праздник установлен\n");

            var answer = new Answer(user.getId(), result.toString());
            answer.getButtons().add(CommandInfo.CheckHoliday.getEmoji());
            answer.getButtons().add(CommandInfo.SetEvent.getEmoji());
            answer.getButtons().add(CommandInfo.Exit.getEmoji());
            parent.out(answer);
            user.setData(Step.Empty);
            return;
        }
        result.append("Не понимаю\n");
        parent.out(new Answer(user.getId(), result.toString()));
    }

    private void setReminderDate(Message msg, User user, IO parent)
    {
        var match = patternDate.matcher(msg.getText());
        StringBuilder result = new StringBuilder();
        if (match.matches())
        {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            usersContext.get(user.getId()).dateReminder = new SimpleDate(day, month);
            Context userContext = usersContext.get(user.getId());
            holidays.setUserHoliday(user.getId(), new Holiday(userContext.date, userContext.name));
            holidays.setUserReminder(user.getId(), new Holiday(userContext.dateReminder, userContext.name));
            result.append("Праздник и напоминания установлены\n");

            var answer = new Answer(user.getId(), result.toString());
            answer.getButtons().add(CommandInfo.CheckHoliday.getEmoji());
            answer.getButtons().add(CommandInfo.SetEvent.getEmoji());
            answer.getButtons().add(CommandInfo.Exit.getEmoji());
            parent.out(answer);
            user.setData(Step.Empty);
            return;
        }
        result.append("Неправильные формат\n");
        parent.out(new Answer(msg.getId(), result.toString()));
    }

    private class Context
    {
        public SimpleDate date;
        public String name;
        public SimpleDate dateReminder;

        public Context(SimpleDate date)
        {
            this.date = date;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Context context = (Context) o;
            return Objects.equals(date, context.date) &&
                    Objects.equals(name, context.name) &&
                    Objects.equals(dateReminder, context.dateReminder);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(date, name, dateReminder);
        }
    }
}
