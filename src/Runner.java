import core.Data.Message;
import core.Data.Source;

public class Runner
{
    public static void main(String[] args)
    {
        var bot = new Handler();
        var terinal = bot.platforms.find(Source.Terminal);
        terinal.out(new Message());
    }
}
