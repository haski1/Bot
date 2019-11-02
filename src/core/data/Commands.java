package core.data;


public enum Commands
{
    Start("/start", "\uD83D\uDE80"),
    Quiz("/startquiz", "\uD83E\uDDE0"),
    Chat("/startchat", "\uD83D\uDE48"),
    Search("/search", "\uD83D\uDD0E"),
    Exit("/exit", "⛔"),
    Help("/help", "❓"),
    Dialog("/dialog"),
    Question("/question"),
    Check("/check");

    private String code;
    private String command;

    Commands(String command)
    {
        this.command = command;
    }

    Commands(String command, String code)
    {
        this.code = code;
        this.command = command;
    }

    public String getCode(){ return code;}

    public String getCommand(){ return command;}
}
