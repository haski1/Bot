package platforms.telegram;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.Source;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TelegramBot extends TelegramLongPollingBot
{
    private IO handler;
    private String name;
    private String token;
    private static final Path configPath = Paths.get(System.getProperty("user.dir") + "\\src\\platforms\\telegram\\config.json");

    public TelegramBot(IO handler)
    {
        this.handler = handler;
        var config = loadConfig();
        name = config.getString("Name");
        token = config.getString("Token");
    }

    private JSONObject loadConfig()
    {
        String configJson = null;
        try
        {
            configJson = Files.readString(configPath, StandardCharsets.UTF_8);
        } catch (IOException e)
        {
            createConfigTemplate();
            System.out.println("Fill in the config");
        }
        JSONObject config = new JSONObject(configJson);
        return config;
    }

    private void createConfigTemplate()
    {
        var template = new JSONObject();
        template.append("Name", "");
        template.append("Token", "");
        try
        {
            Files.writeString(configPath, template.toString(), StandardOpenOption.CREATE);
        }
        catch (IOException ex)
        {
            System.out.println("Сan not create file!");
        }
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage())
        {
            var message = update.getMessage();
            String command = null;
            var text = message.getText();
            var id = message.getChatId().toString();
            if (text.charAt(0) == '/')
            {
                command = text.substring(1).replaceAll("\\s","").toLowerCase();
                text = null;
            }
            var msg = new Message(new ID(id, Source.Telegram), command, text);
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

    public void out(Answer msg)
    {
        if (!(msg.getResult().isEmpty() || msg.getResult() == null))
        {
            var sendMsg = new SendMessage();
            sendMsg.setChatId(msg.getId().getName());
            sendMsg.setText(msg.getResult());
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

}
