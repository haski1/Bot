package platforms.telegram;

import core.IIO;
import core.data.Message;
import core.data.Source;
import core.data.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot
{
    private IIO handler;

    public TelegramBot(IIO handler)
    {
        this.handler = handler;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage())
        {
            var message = update.getMessage();
            var text = message.getText().replaceAll("\\s","");
            if (text.charAt(0) == '/')
                text = text.substring(1);
            var id = message.getChatId().toString();
            var user = new User(id, "basichandler", Source.Telegram);
            var msg = new Message(user, text);
            handler.in(msg);
        }
    }

    @Override
    public String getBotUsername()
    {
        return BotInfo.name;
    }

    @Override
    public String getBotToken()
    {
        return BotInfo.token;
    }

    public void out(Message msg)
    {
        var sendMsg = new SendMessage();
        sendMsg.setChatId(msg.user.id);
        sendMsg.setText(msg.result);
        try
        {
            execute(sendMsg);
        }
        catch (TelegramApiException e)
        {

        }
    }

}
