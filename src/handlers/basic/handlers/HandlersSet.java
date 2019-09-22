package handlers.basic.handlers;

import core.set.BaseSet;
import core.IIO;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

import java.io.IOException;

public class HandlersSet extends BaseSet<String, IIO>
{
    public HandlersSet(IIO handler)
    {
        super();
        register(new Quiz(handler));
        register(new Chat(handler));
    }

    public void register(IIO obj)
    {
        var key = obj.getClass().getSimpleName().toLowerCase();
        super.register(key, obj);
    }
}
