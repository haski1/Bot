package core.command;

import java.util.HashMap;

public class BaseCommandsSet extends HashMap<String, Command>
{
    private Command defaultCommand;

    public BaseCommandsSet()
    {
        super();
    }

    public BaseCommandsSet(Command defaultCommand)
    {
        super();
        this.defaultCommand = defaultCommand;
    }

    protected void register(Command command)
    {
        this.put(command.getName().getCommand().substring(1), command);
    }

    public Command getDefault()
    {
        return defaultCommand;
    }
}
