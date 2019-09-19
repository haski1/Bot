package platforms;

import core.Data.Message;
import core.Data.Source;
import core.Data.User;
import core.IIO;

import java.util.Scanner;

public class TerminalIO implements IIO
{
    private IIO handler;
    private Scanner scanner = new Scanner(System.in);

    public TerminalIO(IIO handler)
    {
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

        var user = new User();
        user.platform = Source.Terminal;
        user.state = "basicscript";
        var message = new Message();
        message.user = user;
        message.instruction = scanner.nextLine();
        in(message);
    }
}
