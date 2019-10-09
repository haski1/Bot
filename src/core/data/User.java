package core.data;

import java.util.HashMap;

public class User
{
    private ID id;
    private State state;
    private HashMap<State, Object> data = new HashMap<>();

    public User(ID id)
    {
        this.id = id;
        this.state = State.Basic;
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

    public ID getId()
    {
        return id;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public Object getData(State key)
    {
        return data.get(key);
    }

    public void setData(State key, Object value)
    {
        data.put(key, value);
    }
}
