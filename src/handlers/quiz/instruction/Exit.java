package handlers.quiz.instruction;

import core.IO;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;

public class Exit implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setState(State.Basic);
    }
}
