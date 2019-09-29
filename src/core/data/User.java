package core.data;

import java.util.HashMap;

public class User
{
    public String id;
    public State state;
    public Source platform;
    public HashMap<State, Object> data = new HashMap<>();

    public User()
    {

    }

    public User(String id, State state, Source platform)
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
        return ((User) obj).id.equals(id) && ((User) obj).platform.equals(platform);
    }

    @Override
    public int hashCode()
    {
        return (id + platform).hashCode();
    }
}
