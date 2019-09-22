package core.data;

public class Message
{
    public User user;
    public String command;
    public String text;
    public String result;
    public boolean done;

    public Message(User user, String command)
    {
        this.user = user;
        this.command = command;
    }

    public Message(User user)
    {
        this.user = user;
    }
}
