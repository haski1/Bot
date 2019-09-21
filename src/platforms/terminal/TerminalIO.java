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
            var text = scanner.nextLine().replaceAll("\\s","");
            text = text.toLowerCase();
            if (text.charAt(0) == '/')
                text = text.substring(1);
            var msg = new Message(user, text);
            in(msg);
        }
    }
}
