package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;

public class Exit implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setModule(Module.Basic);

        if (Search.isSearching(user))
        {
            Search.removeFromSearch(user);
        }
        Search.nonunion(user, parent);
        var answer = new Answer(msg.getId(), "Вы вышли из чата");
        answer.addButton(Emoji.Start.getCode());
        parent.out(answer);
    }
}
