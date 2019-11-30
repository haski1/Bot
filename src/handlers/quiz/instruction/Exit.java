package handlers.quiz.instruction;

import core.IO;
import core.data.*;
import core.command.Command;
import core.data.Module;

public class Exit implements Command
{
    @Override
    public CommandInfo getInfo() {
        return CommandInfo.Exit;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setModule(Module.Basic);
        var answer = new Answer(msg.getId(), "Вы вышли из викторины");
        answer.getButtons().add(CommandInfo.Start.getEmoji());
        parent.out(answer);
    }
}
