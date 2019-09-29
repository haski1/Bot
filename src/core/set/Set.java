package core.set;

import java.util.Collection;

public interface Set<T1, T2>
{
    public boolean contains(T1 key);
    public void register(T1 key, T2 value);
    public T2 find(T1 key);
    public Collection<T2> list();
}
