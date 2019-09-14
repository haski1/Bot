package core;

import architecture.ICommand;
import architecture.IHandler;
import architecture.IManager;

import javax.swing.*;

public class Handler implements IHandler
{
    private IManager manager;

    public Handler(IManager manager)
    {
        this.manager = manager;
    }

    public void handle(ICommand command)
    {
        switch (command.command())
        {
            case "stop":
                System.exit(0);
                break;
            case "choice":
                if (manager.check(command.params()[0]))
                {
                    System.out.println("It is true");
                }
                else
                {
                    System.out.println("It is false");
                }
                break;
            case "next":
                System.out.println(manager.get());
                break;
            default:
                throw new IllegalArgumentException("Unknown command " + command.command());
        }
    }
}
