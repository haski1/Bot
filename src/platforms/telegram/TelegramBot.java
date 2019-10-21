package platforms.telegram;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.IO;
import core.data.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class TelegramBot extends TelegramLongPollingBot
{
    private IO handler;
    private String name;
    private String token;
    private HashMap<String, String> emoji = new HashMap<>();

    public TelegramBot(IO handler, File file) throws IOException {
        this.handler = handler;
        var config = loadConfig(file);
        name = config.name;
        token = config.token;
        if (name.isEmpty() || token.isEmpty())
        {
            System.out.println("Invalid name and token");
            System.exit(0);
        }
        registerEmoji();
    }

    private BotInfo loadConfig(File file) throws IOException {
        try
        {
            return new ObjectMapper().readValue(file, BotInfo.class);
        }
        catch (IOException e)
        {
            createConfigTemplate(file);
            throw new IOException("Error: Fill in the configuration file");
        }
    }

    private void createConfigTemplate(File file) throws IOException {
        try
        {
            new ObjectMapper().writeValue(file, new BotInfo());
        }
        catch (IOException ex)
        {
            throw new IOException("Error: Сan not create file!");
        }
    }

    private void registerEmoji()
    {
        for (var item :Emoji.values())
        {
            emoji.put(item.getCode(), item.getCommand());
        }
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage())
        {
            var message = update.getMessage();
            String command;
            var text = message.getText();
            var id = message.getChatId().toString();
            ID userId = new ID(id, Platform.Telegram);
            if (emoji.containsKey(text))
            {
                text = emoji.get(text);
            }
            Message msg;
            if (text.charAt(0) == '/')
            {
                command = text.substring(1).replaceAll("\\s", "").toLowerCase();
                msg = new Message(userId, command, null);
            }
            else
            {
                msg = new Message(userId, null, text);
            }
            handler.in(msg);
        }
    }

    @Override
    public String getBotUsername()
    {
        return name;
    }

    @Override
    public String getBotToken()
    {
        return token;
    }

    public void out(Answer ans)
    {
        var sendMsg = new SendMessage();
        sendMsg.setChatId(ans.getId().getName());
        sendMsg.setText(ans.getResult());
        if (!(ans.getButtons().isEmpty()))
        {
            var markup = new ReplyKeyboardMarkup();
            markup.setResizeKeyboard(true);
            var row = new KeyboardRow();
            row.addAll(ans.getButtons());
            var rows = Collections.singletonList(row);
            markup.setKeyboard(rows);
            sendMsg.setReplyMarkup(markup);
        }
        try
        {
            execute(sendMsg);
        }
        catch (TelegramApiException e)
        {
            System.out.println("Telegram: message did not send");
        }
    }

}
