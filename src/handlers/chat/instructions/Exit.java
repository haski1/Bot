package handlers.chat.instructions;

import core.IO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;

public class Exit extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.user.state = State.BasicHandler;
        msg.done = true;
        if (Search.isSearching(msg.user))
        {
            Search.removeFromSearch(msg.user);
        }
        Search.nonunion(msg.user, handler);
    }
}
