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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class TelegramBot extends TelegramLongPollingBot
{
    private IO handler;
    private String name;
    private String token;
    private HashMap<String, String> emoji = new HashMap<>();
    private static final Path configPath = Paths.get(System.getProperty("user.dir") + "\\src\\platforms\\telegram\\config.json");

    public TelegramBot(IO handler)
    {
        this.handler = handler;
        var config = loadConfig();
        name = config.getString("Name");
        token = config.getString("Token");
        associateEmoji();
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
        return new JSONObject(configJson);
    }

    private void associateEmoji()
    {
        emoji.put("\uD83E\uDDE0", "/startquiz");
        emoji.put("\uD83D\uDE48", "/startchat");
        emoji.put("\uD83D\uDD0E", "/search");
        emoji.put("\uD83D\uDE80", "/start");
        emoji.put("⛔", "/exit");
        emoji.put("❓", "/help");
    }


    private void createConfigTemplate()
    {
        var template = new JSONObject();
        template.append("Name", "");
        template.append("Token", "");
        try
        {
            Files.writeString(configPath, template.toString(), StandardOpenOption.CREATE);
        } catch (IOException ex)
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
            if (emoji.containsKey(text))
            {
                text = emoji.get(text);
            }
            if (text.charAt(0) == '/')
            {
                command = text.substring(1).replaceAll("\\s", "").toLowerCase();
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

    public void out(Answer ans)
    {
        var sendMsg = new SendMessage();
        sendMsg.setChatId(ans.getId().getName());
        sendMsg.setText(ans.getResult());
        if (!(ans.getButtons().isEmpty()))
        {
            var markup = new ReplyKeyboardMarkup();
            markup.setResizeKeyboard(true);
            var rows = new ArrayList<KeyboardRow>();
            rows.add(ans.getButtons());
            markup.setKeyboard(rows);
            sendMsg.setReplyMarkup(markup);
        }

        try
        {
            execute(sendMsg);
        } catch (TelegramApiException e)
        {
            System.out.println("Telegram: message did not send");
        }
    }

}
