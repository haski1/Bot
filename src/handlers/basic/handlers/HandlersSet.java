package handlers.basic.handlers;

import core.IO;
import core.data.ID;
import core.data.Module;
import core.data.User;
import handlers.calendar.Calendar;
import handlers.chat.Chat;
import handlers.quiz.Quiz;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HandlersSet extends HashMap<Module, IO>
{
    public HandlersSet(IO handler, ConcurrentHashMap<ID, User> users)
    {
        super();
        try
        {
            register(new Quiz(handler, users, Paths.get("resources/questions.txt")));
            System.out.println("load module Quiz - ok");
        }
        catch (IOException e)
        {
            System.out.println(" module Quiz - fail");
        }
        try
        {
            register(new Chat(handler, users));
            System.out.println("load module Chat - ok");
        }
        catch (Exception e)
        {
            System.out.println("load module Chat - fail");
        }
        try
        {
            register(new Calendar(handler, users, Paths.get("resources/holidays.txt")));
            System.out.println("load module Calendar - ok");
        }
        catch (Exception e)
        {
            System.out.println("load module Calendar - fail");
        }

    }

    private void register(IO obj)
    {
        var key = Module.valueOf(obj.getClass().getSimpleName());
        this.put(key, obj);
    }
}
