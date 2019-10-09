package handlers.chat.instructions;

import core.command.BaseCommandsSet;
import core.command.Command;

public class ChatInstructionSet extends BaseCommandsSet
{
    public ChatInstructionSet()
    {
        super();
        defaultCommand = new Dialog();
        register(defaultCommand);
        register(new Search());
        register(new Exit());
    }
}
