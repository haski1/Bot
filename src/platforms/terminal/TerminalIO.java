package platforms.terminal;

import core.data.*;
import core.IO;

import java.util.Scanner;

public class TerminalIO implements IO
{
    private ID id;
    private IO handler;
    private Scanner scanner = new Scanner(System.in);

    public TerminalIO(IO handler)
    {
        id = new ID("terminal", Platform.Terminal);
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        handler.in(msg);
    }

    @Override
    public void out(Answer msg)
    {
        System.out.println(msg.getResult());
    }

    public void run()
    {
        while (true)
        {
            var text = scanner.nextLine();
            String command = null;
            if (text.charAt(0) == '/')
            {
                command = text.substring(1).replaceAll("\\s","").toLowerCase();
                text = null;
            }
            var msg = new Message(id, command, text);
            in(msg);
        }
    }

}
