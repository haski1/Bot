package core;

public class Handler implements IHandler
{
    public void handle(ICommand command)
    {
        switch (command.command())
        {
            case "start":
                System.out.println("You are started!");
            case "stop":
                System.out.println("You are stopped!");
            case "choice":
                System.out.println("You made choice" + command.params()[0]);
            case "next":
                System.out.println("Next question");
            default:
                throw new IllegalArgumentException("Unknown command " + command.command());
        }
    }
}
