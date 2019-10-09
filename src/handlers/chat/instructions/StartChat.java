package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

public class StartChat implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setState(State.Chat);
        var result = "Вы вошли в анонимный чат\nВыход: /exit";
        var answer = new Answer(user.getId(), result);
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("⛔");
        }
        parent.out(answer);
        parent.in(new Message(msg.getId(), "search"));
    }
}
