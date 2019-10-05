package platforms;

import core.IO;
import core.data.Source;
import platforms.telegram.TelegramIO;
import platforms.terminal.TerminalIO;

import java.util.HashMap;

public class PlatformsSet extends HashMap<Source, IO>
{
    public PlatformsSet(IO handler)
    {
        super();
        this.put(Source.Terminal, new TerminalIO(handler));
        this.put(Source.Telegram, new TelegramIO(handler));
    }
}
