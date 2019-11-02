package core.command;

import core.IO;
import core.data.Commands;
import core.data.Message;
import core.data.User;

public interface Command
{
    Commands getName();

    void execute(Message msg, User user, IO parent);
}
