package core.data;

public class Message
{
    private ID id;
    private String command;
    private String text;

    public Message(ID id)
    {
        this.id = id;
        this.command = null;
    }

    public Message(ID id, String command)
    {
        this.id = id;
        this.command = command;
    }

    public Message(ID id, String command, String text)
    {
        this.id = id;
        this.text = text;
        this.command = command;
    }

    public ID getId()
    {
        return id;
    }

    public String getCommand()
    {
        return command;
    }

    public String getText()
    {
        return text;
    }
}
