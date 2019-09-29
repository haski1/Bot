package handlers.basic.handlers;

import core.IO;
import core.data.State;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

import java.util.HashMap;

public class HandlersSet extends HashMap<State, IO>
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
        this.put(key, obj);
    }
}
