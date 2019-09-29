package platforms.telegram;

import core.IO;
import core.data.Message;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class TelegramIO implements IO
{
    private IO handler;
    private TelegramBot bot;

    public TelegramIO(IO handler)
    {
        this.handler = handler;
        ApiContextInitializer.init();

        this.bot = new TelegramBot(this);
        var api = new TelegramBotsApi();

        try
        {
            api.registerBot(this.bot);
        }
        catch (TelegramApiRequestException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void in(Message msg)
    {
        handler.in(msg);
    }

    @Override
    public void out(Message msg)
    {
        bot.out(msg);
    }
}
