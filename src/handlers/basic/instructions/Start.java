package handlers.basic.instructions;

import core.IO;
import core.data.*;
import core.command.Command;

public class Start implements Command
{
    public void execute(Message msg, User user, IO parent)
    {
        var result = "Привет! \nЭто iwanttoseebot\nПиши /startquiz чтобы проиграть в викторину!\n" +
                "Пиши /startchat зайти в анонимный чат!";
        var answer = new Answer(msg.getId(), result);
        answer.addButton(Emoji.Chat.getCode());
        answer.addButton(Emoji.Quiz.getCode());
        parent.out(answer);
    }
}