package handlers.chat.instructions;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;

public class Dialog implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var objUser = user.getData(State.Chat);
        if (objUser != null)
        {
            var userTwo = (User)objUser;
            var answer = new Answer(userTwo.getId(), msg.getText());
            parent.out(answer);
        }
    }
}
