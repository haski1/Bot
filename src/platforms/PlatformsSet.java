package platforms;

import core.data.Source;
import core.IO;
import core.set.BaseSet;
import platforms.telegram.TelegramIO;
import platforms.terminal.TerminalIO;

public class PlatformsSet extends BaseSet<Source, IO>
{
    public PlatformsSet(IO handler)
    {
        super();
        super.register(Source.Terminal, new TerminalIO(handler));
        super.register(Source.Telegram, new TelegramIO(handler));
    }
}
