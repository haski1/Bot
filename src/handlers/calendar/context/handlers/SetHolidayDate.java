package handlers.calendar.context.handlers;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.User;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;
import handlers.calendar.data.SimpleDate;

public class SetHolidayDate implements ContextHandler
{
    private ContextHandler nextHandler;

    public SetHolidayDate(ContextHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }

    public ContextHandler handle(Context context, Message msg, User user, IO parent)
    {
        SimpleDate date = SimpleDate.parse(msg.getText());

        if (date == null)
        {
            parent.out(new Answer(user.getId(), "Неправильный формат\n"));
            return this;
        }

        context.date = date;
        parent.out(new Answer(user.getId(), "Напишите название\n"));
        return nextHandler;
    }
}
