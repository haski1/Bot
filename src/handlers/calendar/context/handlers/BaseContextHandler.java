package handlers.calendar.context.handlers;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.User;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;

public class BaseContextHandler implements ContextHandler
{
    public ContextHandler handle(Context context, Message msg, User user, IO parent)
    {
        parent.out(new Answer(user.getId(), "Не понимаю"));
        return this;
    }
}
