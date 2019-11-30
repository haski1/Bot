package platforms;

import core.IO;
import core.data.Platform;
import platforms.telegram.TelegramIO;
import platforms.terminal.TerminalIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PlatformsSet extends HashMap<Platform, IO>
{
    public PlatformsSet(IO handler)
    {
        super();
        //try
        //{
        //    this.put(Platform.Telegram, new TelegramIO(handler, new File("resources/config.json")));
        //   System.out.println("load platform Telegram - ok");
        //}
        //catch (IOException e)
        //{
        //    System.out.println("load platform Telegram - fail");
        //    System.out.println(e.getMessage());
        //}
        try
        {
            this.put(Platform.Terminal, new TerminalIO(handler));
            System.out.println("load platform Terminal - ok");
        }
        catch (Exception e)
        {
            System.out.println("load platform Terminal - fail");
        }
    }
}
