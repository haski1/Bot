package handlers.calendar.instructions;

import core.IO;
import core.command.Command;
import core.data.*;
import handlers.calendar.context.Context;
import handlers.calendar.data.DataBaseHolidays;
import handlers.calendar.data.Holiday;
import handlers.calendar.data.SimpleDate;

import java.util.HashMap;
import java.util.Objects;

public class HandleContext implements Command
{
    @Override
    public CommandInfo getInfo()
    {
        return CommandInfo.HandleContext;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        Context context = (Context)user.getData();
        if (context == null)
        {
            parent.out(new Answer(user.getId(), "Не понимаю"));
            return;
        }
        context.handler = context.handler.handle(context, msg, user, parent);
    }
}
