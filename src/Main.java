import handlers.basic.BasicHandler;

public class Main
{
    public static void main(String[] args)
    {
        var bot = new BasicHandler();
        if (args.length > 0 && args[0].equals("-q"))
        {
            bot.runQuietly();
        }
        else
        {
            bot.runWithTerminal();
        }
    }
}
