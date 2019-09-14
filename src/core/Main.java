package core;

import architecture.IHandler;
import architecture.IParser;

public class Main
{
    public static void main(String[] args)
    {
        IHandler handler = new Handler();
        IParser parser = new Parser();
        Reader reader = new Reader(handler, parser);
        reader.run();
    }
}
