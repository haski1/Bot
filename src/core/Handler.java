package core;

import architecture.ICommand;
import architecture.IHandler;

public class Handler implements IHandler
{
    public Handler()
    {
    }

    public void handle(ICommand command)
    {
        switch (command.command())
        {
            case "start":
                System.out.println("You are started!");
                break;
            case "stop":
                System.out.println("You are stopped!");
                break;
            case "choice":
                System.out.println("You made choice" + command.params()[0]);
                break;
            case "next":
                System.out.println("Next question");
                break;
            default:
                throw new IllegalArgumentException("Unknown command " + command.command());
        }
    }
}
