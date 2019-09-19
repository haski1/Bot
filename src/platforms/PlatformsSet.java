package platforms;

import core.Data.Source;
import core.IIO;

import java.util.HashMap;

public class PlatformsSet
{
    private HashMap<Source, IIO> platforms;

    public PlatformsSet(IIO handler)
    {
        platforms = new HashMap<Source, IIO>();

        register(Source.Terminal, new TerminalIO(handler));
        register(Source.Telegram, new TelegramIO(handler));
    }

    public void register(Source source, IIO obj)
    {
        platforms.put(source, obj);
    }

    public IIO find(Source source)
    {
        if (contains(source))
            return platforms.get(source);
        return null;
    }


    public boolean contains(Source source)
    {
        return platforms.containsKey(source);
    }
}
