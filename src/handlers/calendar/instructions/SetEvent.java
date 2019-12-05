package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;

public class SetEvent implements Command
{
    private ContextHandler setHolidayDate;

    public SetEvent(ContextHandler setHolidayDate)
    {
        this.setHolidayDate = setHolidayDate;
    }

    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.SetEvent;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        user.setData(new Context(setHolidayDate));
        var answer = new Answer(user.getId(), "Напишите дату\nФормат дд.мм\nПример 01.01\n");
        answer.getButtons().add(CommandInfo.Exit.getEmoji());
        parent.out(answer);
    }
}
