package core.data;

public enum Emoji
{
    Start("\uD83D\uDE80", "/start"),
    Quiz("\uD83E\uDDE0", "/startquiz"),
    Chat("\uD83D\uDE48", "/startchat"),
    Search("\uD83D\uDD0E", "/search"),
    Stop("⛔", "/exit"),
    Help("❓", "/help");

    private String code;
    private String command;

    Emoji(String code, String command)
    {
        this.code = code;
        this.command = command;
    }
    public String getCode(){ return code;}
    public String getCommand(){ return command;}
}
