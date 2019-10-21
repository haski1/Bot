package core.data;

public enum Module
{
    Basic("Наберите команду /start чтобы узнать больше!", Emoji.Start.getCode()),
    Quiz("Пиши /startquiz чтобы проиграть в викторину!\n", Emoji.Quiz.getCode()),
    Chat("Пиши /startchat зайти в анонимный чат!", Emoji.Chat.getCode());

    private String prompt;
    private String button;

    Module(String prompt, String button)
    {
        this.prompt = prompt;
        this.button = button;
    }

    public String getPrompt()
    {
        return this.prompt;
    }

    public String getButton()
    {
        return this.button;
    }
}
