package handlers.chat.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.Commands;
import core.data.Message;
import core.data.User;

public class Dialog implements Command
{
    @Override
    public Commands getName()
    {
        return Commands.Dialog;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var objUser = user.getData();
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            parent.out(new Answer(userTwo.getId(), msg.getText()));
        }
    }
}
