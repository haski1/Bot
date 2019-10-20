package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

public class Exit implements Command
{
    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setState(State.Basic);

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
