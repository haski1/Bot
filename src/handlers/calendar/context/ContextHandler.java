package handlers.calendar.context;

import core.IO;
import core.data.Message;
import core.data.User;

public interface ContextHandler
{
    ContextHandler handle(Context context, Message msg, User user, IO parent);
}
