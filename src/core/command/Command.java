package core.command;

import core.IO;
import core.data.Message;
import core.data.User;

public interface Command
{
    default String getName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

    void execute(Message msg, User user, IO parent);
}
