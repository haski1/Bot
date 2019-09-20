package platforms.terminal;

import core.data.Message;
import core.data.Source;
import core.data.User;
import core.IIO;

import java.util.Scanner;

public class TerminalIO implements IIO
{
    private User user;
    private IIO handler;
    private Scanner scanner = new Scanner(System.in);

    public TerminalIO(IIO handler)
    {
        user = new User("terminal", "basichandler", Source.Terminal);
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        handler.in(msg);
    }

    @Override
    public void out(Message msg)
    {
        System.out.println(msg.result);
    }

    public void run()
    {
        while (true)
        {
            var input = scanner.nextLine().replaceAll("\\s","");
            if (input.charAt(0) == '/')
                input = input.substring(1);
            var msg = new Message(user, input);
            in(msg);
        }
    }
}
