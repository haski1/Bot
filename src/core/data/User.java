package core.data;

import java.util.HashMap;

public class User
{
    private ID id;
    private Module module;
    private HashMap<Module, Object> data = new HashMap<>();

    public User(ID id)
    {
        this.id = id;
        this.module = Module.Basic;
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

    public Module getModule()
    {
        return module;
    }

    public void setModule(Module module)
    {
        this.module = module;
    }

    public Object getData(Module key)
    {
        return data.get(key);
    }

    public void setData(Module key, Object value)
    {
        data.put(key, value);
    }
}
