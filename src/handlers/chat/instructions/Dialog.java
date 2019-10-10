package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

public class Dialog implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var objUser = user.getData(State.Chat);
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            parent.out(new Answer(userTwo.getId(), msg.getText()));
        }
    }
}
