package handlers.chat.instructions;

import core.IIO;
import core.data.Message;
import core.data.User;
import core.instruction.BaseInstruction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Search extends BaseInstruction
{
    private static Deque<User> freeUsers = new ArrayDeque<>();

    public static void addToSearch(User user)
    {
        freeUsers.addLast(user);
    }

    public static void removeFromSearch(User user)
    {
        freeUsers.remove(user);
    }

    public static boolean isSearching(User user)
    {
        return freeUsers.contains(user);
    }

    public Search()
    {
    }

    @Override
    public void execute(Message msg, IIO handler)
    {
        if (msg.user.data.get("chat") != null)
        {
            nonunion(msg.user, handler);
        }
        if (isSearching(msg.user))
        {
            return;
        }
        addToSearch(msg.user);
        msg.result = "Ищем собеседника...";
        msg.done = true;
        handler.out(msg);

        if (freeUsers.size() >= 2)
        {
            var first = freeUsers.pop();
            var second = freeUsers.pop();
            union(first, second, handler);
        }
    }

    private void union(User first, User second, IIO handler)
    {
        first.data.put("chat", second);
        second.data.put("chat", first);
        var result = "Собеседник найден!\n(Предепреджение: вы общаетесь с реальным человеком!)";

        var msgToFirst = new Message(first);
        var msgToSecond = new Message(second);
        msgToFirst.result = msgToSecond.result = result;
        handler.out(msgToFirst);
        handler.out(msgToSecond);
    }

    public static void nonunion(User user, IIO handler)
    {
        var userTwo = user.data.get("chat");
        if (userTwo != null)
        {
            ((User)userTwo).data.put("chat", null);
            user.data.put("chat", null);
            var result = "Собеседник вышел\n Для поиска напишите команду /search";
            var answer = new Message((User)userTwo);
            answer.result = result;
            handler.out(answer);
        }
    }

}
