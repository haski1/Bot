package handlers.chat.instructions;

import core.command.BaseCommandsSet;
import core.data.User;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatInstructionSet extends BaseCommandsSet
{
    public ChatInstructionSet(ConcurrentLinkedQueue<User> searchingUsers)
    {
        super(new Dialog());
        register(new Search(searchingUsers));
        register(new Exit(searchingUsers));
    }
}
