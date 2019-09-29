package handlers.basic;

import core.data.User;
import core.set.Set;

import java.util.Collection;
import java.util.HashMap;

public class Users implements Set<User, User>
{
    private HashMap<User, User> users;

    public Users()
    {
        users = new HashMap<User, User>();
    }

    public void register(User user)
    {
        register(user, user);
    }

    @Override
    public boolean contains(User key)
    {
        return users.containsKey(key);
    }

    @Override
    public void register(User key, User value)
    {
        users.put(key, value);
    }


    @Override
    public User find(User key)
    {
        if (contains(key))
            return users.get(key);
        return null;
    }

    @Override
    public Collection<User> list()
    {
        return users.values();
    }

    public void remove(User key)
    {
        users.remove(key);
    }
}
