package handlers.basic.handlers;

import core.data.State;
import core.set.BaseSet;
import core.IIO;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

import java.io.IOException;

public class HandlersSet extends BaseSet<State, IIO>
{
    public HandlersSet(IIO handler)
    {
        super();
        register(new Quiz(handler));
        register(new Chat(handler));
    }

    public void register(IIO obj)
    {
        var key = State.valueOf(obj.getClass().getSimpleName());
        super.register(key, obj);
    }
}
