package core;

import architecture.ICommand;

public class Command implements ICommand
{
    private String command;
    private String[] params;

    public Command(String command, String[] params)
    {
        this.command = command;
        this.params = params;
    }

    public String command()
    {
        return command;
    }

    public String[] params()
    {
        return params;
    }
}
