package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;

public class Dialog implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var objUser = user.getData(Module.Chat);
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            parent.out(new Answer(userTwo.getId(), msg.getText()));
        }
    }
}
