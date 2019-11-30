package core.data;


public enum CommandInfo
{
    Start("/start", "\uD83D\uDE80"),
    Quiz("/startquiz", "\uD83E\uDDE0"),
    Chat("/startchat", "\uD83D\uDE48"),
    Calendar("/startcalendar", "📆"),
    Search("/search", "\uD83D\uDD0E"),
    Exit("/exit", "⛔"),
    Help("/help", "❓"),
    Dialog("/dialog"),
    Question("/question"),
    Check("/check"),
    CheckHoliday("/checkholiday", "\uD83C\uDF89"),
    SetHoliday("/setholiday");

    private String emoji;
    private String command;

    CommandInfo(String command)
    {
        this.command = command;
    }

    CommandInfo(String name, String emoji)
    {
        this.emoji = emoji;
        this.command = name;
    }

    public String getEmoji(){ return emoji;}

    public String getName(){ return command;}
}
