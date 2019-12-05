package handlers.calendar.context.handlers;

import core.IO;
import core.data.Answer;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;
import handlers.calendar.data.DataBaseHolidays;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;

public class SetReminderDate implements ContextHandler
{
    private ContextHandler baseHandler;
    private DataBaseHolidays dataBaseHolidays;

    public SetReminderDate(ContextHandler baseHandler, DataBaseHolidays dataBaseHolidays)
    {
        this.baseHandler = baseHandler;
        this.dataBaseHolidays = dataBaseHolidays;
    }

    @Override
    public ContextHandler handle(Context context, Message msg, User user, IO parent)
    {
        SimpleDate date = SimpleDate.parse(msg.getText());

        if (date == null)
        {
            parent.out(new Answer(user.getId(), "Неправильный формат\n"));
            return this;
        }
        context.dateReminder = date;
        dataBaseHolidays.setUserReminder(user.getId(), context.dateReminder, new Holiday(context.date, context.name));
        var answer = new Answer(user.getId(), "Напоминание установлено\n");
        answer.getButtons().add(CommandInfo.CheckHoliday.getEmoji());
        answer.getButtons().add(CommandInfo.SetEvent.getEmoji());
        answer.getButtons().add(CommandInfo.Exit.getEmoji());
        parent.out(answer);
        return baseHandler;
    }
}
