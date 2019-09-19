package core.Data;

public class User
{
    public String id;
    public String state;
    public Source platform;

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof User))
            return false;
        return ((User)obj).id.equals(id);
    }

    @Override
    public int hashCode()
    {
        return id.hashCode();
    }
}
