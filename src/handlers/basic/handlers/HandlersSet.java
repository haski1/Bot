package handlers.basic.handlers;

import core.IO;
import core.data.ID;
import core.data.State;
import core.data.User;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

import java.util.HashMap;

public class HandlersSet extends HashMap<State, IO>
{
    public HandlersSet(IO handler, HashMap<ID, User> users)
    {
        super();
        register(new Quiz(handler, users));
        register(new Chat(handler, users));
    }

    private void register(IO obj)
    {
        var key = State.valueOf(obj.getClass().getSimpleName());
        this.put(key, obj);
    }
}
