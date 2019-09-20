package core.data;

public class User
{
    public String id;
    public String state;
    public Source platform;

    public User()
    {

    }

    public User(String id, String state, Source platform)
    {
        this.id = id;
        this.state = state;
        this.platform = platform;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof User))
            return false;
        return ((User) obj).id.equals(id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
