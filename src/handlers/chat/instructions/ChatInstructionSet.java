package handlers.chat.instructions;

import core.command.BaseCommandsSet;

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
