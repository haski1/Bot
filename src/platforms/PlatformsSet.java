package platforms;

import core.data.Source;
import core.IIO;
import core.set.BaseSet;
import platforms.telegram.TelegramIO;
import platforms.terminal.TerminalIO;

public class PlatformsSet extends BaseSet<Source, IIO>
{
    public PlatformsSet(IIO handler)
    {
        super();
        super.register(Source.Terminal, new TerminalIO(handler));
        super.register(Source.Telegram, new TelegramIO(handler));
    }
}
