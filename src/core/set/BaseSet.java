package core.set;

import java.util.Collection;
import java.util.HashMap;

public class BaseSet<T1, T2> implements Set<T1, T2>
{
    private HashMap<T1, T2> set;

    public BaseSet()
    {
        set = new HashMap<T1, T2>();
    }

    @Override
    public boolean contains(T1 key)
    {
        return set.containsKey(key);
    }

    @Override
    public void register(T1 key, T2 value)
    {
        set.put(key, value);
    }

    @Override
    public T2 find(T1 key)
    {
        if (contains(key))
            return set.get(key);
        return null;
    }

    @Override
    public Collection<T2> list()
    {
        return set.values();
    }
}
