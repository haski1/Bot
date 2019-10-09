package handlers.chat.instructions;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;

public class StartChat implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setState(State.Chat);
        var result = "Вы вошли в анонимный чат\nВыход: /exit";
        parent.out(new Answer(msg.getId(), result));
        parent.in(new Message(msg.getId(), "search"));
    }
}
