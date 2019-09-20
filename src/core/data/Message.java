package core.data;

public class Message
{
    public User user;
    public String instruction;
    public String result;
    public boolean done;

    public Message(User user, String instruction)
    {
        this.user = user;
        this.instruction = instruction;
    }
}
