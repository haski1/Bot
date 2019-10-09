package core.command;

import java.util.HashMap;

public class BaseCommandsSet extends HashMap<String, Command>
{
    protected Command defaultCommand;

    public BaseCommandsSet()
    {
        super();
    }

    protected void register(Command command)
    {
        this.put(command.getName(), command);
    }

    public Command getDefault()
    {
        return defaultCommand;
    }
}
