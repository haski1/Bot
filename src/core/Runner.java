package core;

import architecture.ICommand;
import architecture.IHandler;
import architecture.IParser;

public class Runner
{
    private static IHandler handler;
    private static IParser parser;
    private static Reader reader;


    public static void main(String[] args)
    {
        initialize();
        run();
    }

    private static void run()
    {
        while(true)
        {
            ICommand cmd = reader.nextCommand();
            handler.handle(cmd);
        }
    }

    private static void initialize()
    {
        handler = new Handler();
        parser = new Parser();
        reader = new Reader(parser);
    }
}
