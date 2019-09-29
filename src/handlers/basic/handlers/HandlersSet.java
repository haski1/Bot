package handlers.basic.handlers;

import core.data.State;
import core.set.BaseSet;
import core.IO;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

public class HandlersSet extends BaseSet<State, IO>
{
    public HandlersSet(IO handler)
    {
        super();
        register(new Quiz(handler));
        register(new Chat(handler));
    }

    public void register(IO obj)
    {
        var key = State.valueOf(obj.getClass().getSimpleName());
        super.register(key, obj);
    }
}
