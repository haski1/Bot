package platforms.telegram;

import core.IO;
import core.data.Answer;
import core.data.Message;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.File;
import java.io.IOException;

public class TelegramIO implements IO
{
    private IO handler;
    private TelegramBot bot;

    public TelegramIO(IO handler, File file) throws IOException {
        this.handler = handler;
        ApiContextInitializer.init();

        this.bot = new TelegramBot(this, file);
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
    public void out(Answer msg)
    {
        bot.out(msg);
    }
}
