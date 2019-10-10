package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class Search implements Command
{
    private static Deque<User> freeUsers = new ArrayDeque<>();

    private static void addToSearch(User user)
    {
        freeUsers.addLast(user);
    }

    static void removeFromSearch(User user)
    {
        freeUsers.remove(user);
    }

    static boolean isSearching(User user)
    {
        return freeUsers.contains(user);
    }

    private void union(User first, User second, IO handler)
    {
        first.setData(State.Chat, second);
        second.setData(State.Chat, first);
        var result = "Собеседник найден!\n(Предепреджение: вы общаетесь с реальным человеком!)";

        var ansToFirst = new Answer(first.getId(), result);
        var ansToSecond = new Answer(second.getId(), result);
        handler.out(ansToFirst);
        handler.out(ansToSecond);
    }

    public static void nonunion(User user, IO handler)
    {
        var objUser = user.getData(State.Chat);
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            userTwo.setData(State.Chat, null);
            user.setData(State.Chat, null);

            var result = "Собеседник вышел\n Для поиска нового собеседника напишите команду /search";
            var answer = new Answer(userTwo.getId(), result);
            if (user.getId().getPlatform() == Source.Telegram)
            {
                answer.addButton("\uD83D\uDD0E");
                answer.addButton("⛔");
            }
            handler.out(answer);
        }
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        if (user.getData(State.Chat) != null)
        {
            nonunion(user, parent);
        }
        if (isSearching(user))
        {
            return;
        }
        addToSearch(user);
        var result = "Ищем собеседника...";
        var answer = new Answer(user.getId(), result);
        if (user.getId().getPlatform() == Source.Telegram)
        {
            answer.addButton("⛔");
        }
        parent.out(answer);


        if (freeUsers.size() >= 2)
        {
            var first = freeUsers.pop();
            var second = freeUsers.pop();
            union(first, second, parent);
        }
    }
}
