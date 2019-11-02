package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;

public class StartChat implements Command
{
    @Override
    public Commands getName()
    {
        return Commands.Chat;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setModule(Module.Chat);
        var result = "Вы вошли в анонимный чат\nДля поиска собеседника напиши команду /search\nВыход: /exit";
        var answer = new Answer(user.getId(), result);
        answer.getButtons().add(Commands.Search.getCode());
        answer.getButtons().add(Commands.Exit.getCode());
        parent.out(answer);
    }
}
