package scripts;

import core.IIO;
import scripts.basic.BasicScript;

import java.util.HashMap;

public class ScriptsSet
{
    private HashMap<String, IIO> scripts;

    public ScriptsSet(IIO handler)
    {
        scripts = new HashMap<String, IIO>();

        register(new BasicScript(handler));
    }

    public void register(IIO obj)
    {
        var name = obj.getClass().getSimpleName().toLowerCase();
        scripts.put(name, obj);
    }

    public IIO find(String name)
    {
        if (contains(name))
            return scripts.get(name);
        return null;
    }


    public boolean contains(String source)
    {
        return scripts.containsKey(source);
    }
}
