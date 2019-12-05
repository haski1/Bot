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

public class SetReminder implements ContextHandler
{
    private ContextHandler nextHandler;
    private ContextHandler baseHandler;
    private DataBaseHolidays dataBaseHolidays;

    public SetReminder(ContextHandler nextHandler, ContextHandler baseHandler, DataBaseHolidays dataBaseHolidays)
    {
        this.nextHandler = nextHandler;
        this.baseHandler = baseHandler;
        this.dataBaseHolidays = dataBaseHolidays;
    }

    @Override
    public ContextHandler handle(Context context, Message msg, User user, IO parent)
    {
        dataBaseHolidays.setUserHoliday(user.getId(), new Holiday(context.date, context.name));

        if ("да".equals(msg.getText().toLowerCase()))
        {
            var answer = new Answer(user.getId(), "Напишите дату\n" + "Формат дд.мм\n" + "Пример 01.01\n");
            answer.getButtons().add(CommandInfo.Exit.getEmoji());
            parent.out(answer);
            return nextHandler;
        }
        if ("нет".equals(msg.getText().toLowerCase()))
        {
            var answer = new Answer(user.getId(), "Праздник установлен\n");
            answer.getButtons().add(CommandInfo.CheckHoliday.getEmoji());
            answer.getButtons().add(CommandInfo.SetEvent.getEmoji());
            answer.getButtons().add(CommandInfo.Exit.getEmoji());
            parent.out(answer);
            return baseHandler;
        }

        parent.out(new Answer(user.getId(), "Не понимаю\n"));
        return this;
    }
}
