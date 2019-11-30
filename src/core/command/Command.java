package core.command;

import core.IO;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;

public interface Command
{
    CommandInfo getInfo();

    void execute(Message msg, User user, IO parent);
}
