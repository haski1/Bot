package handlers.basic.handlers;

import core.set.BaseSet;
import core.IIO;

public class HandlersSet extends BaseSet<String, IIO>
{
    public HandlersSet(IIO handler)
    {
        super();
    }

    public void register(IIO obj)
    {
        var key = obj.getClass().getSimpleName().toLowerCase();
        super.register(key, obj);
    }
}
