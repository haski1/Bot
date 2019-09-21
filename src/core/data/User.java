package core.data;

import java.util.HashMap;

public class User<T>
{
    public String id;
    public String state;
    public Source platform;
    public HashMap<String, T> data = new HashMap<>();

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
