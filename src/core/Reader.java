package core;

import java.util.Scanner;

public class Reader
{
    private IHandler handler;
    private IParser parser;
    private Scanner scanner;
    private boolean isRunning;

    public Reader(IHandler handler, IParser parser)
    {
        this.handler = handler;
        this.parser = parser;
        scanner = new Scanner(System.in);
    }

    public void run()
    {
        isRunning = true;
        while (isRunning)
        {
            String input = scanner.next();
            ICommand cmd = parser.parse(input);
            handler.handle(cmd);
        }
    }

    public void stop()
    {
        isRunning = false;
    }
}