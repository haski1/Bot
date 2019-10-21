package handlers.chat.instructions;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;

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
        first.setData(Module.Chat, second);
        second.setData(Module.Chat, first);
        var result = "Собеседник найден!\n(Предепреджение: вы общаетесь с реальным человеком!)";

        var ansToFirst = new Answer(first.getId(), result);
        var ansToSecond = new Answer(second.getId(), result);
        handler.out(ansToFirst);
        handler.out(ansToSecond);
    }

    public static void nonunion(User user, IO handler)
    {
        var objUser = user.getData(Module.Chat);
        if (objUser != null)
        {
            var userTwo = (User) objUser;
            userTwo.setData(Module.Chat, null);
            user.setData(Module.Chat, null);

            var result = "Собеседник вышел\n Для поиска нового собеседника напишите команду /search";
            var answer = new Answer(userTwo.getId(), result);
            answer.addButton(Emoji.Search.getCode());
            answer.addButton(Emoji.Stop.getCode());
            handler.out(answer);
        }
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        if (user.getData(Module.Chat) != null)
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
        answer.addButton(Emoji.Stop.getCode());
        parent.out(answer);


        if (freeUsers.size() >= 2)
        {
            var first = freeUsers.pop();
            var second = freeUsers.pop();
            union(first, second, parent);
        }
    }
}
