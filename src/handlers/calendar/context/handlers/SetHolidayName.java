package handlers.calendar.context.handlers;

import core.IO;
import core.data.Answer;
import core.data.CommandInfo;
import core.data.Message;
import core.data.User;
import handlers.calendar.context.Context;
import handlers.calendar.context.ContextHandler;

public class SetHolidayName implements ContextHandler
{
    private ContextHandler nextHandler;

    public SetHolidayName(ContextHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }

    @Override
    public ContextHandler handle(Context context, Message msg, User user, IO parent)
    {
        context.name = msg.getText();
        var answer = new Answer(user.getId(), "Добавить напоминание?\n");
        answer.getButtons().add(CommandInfo.Yes.getEmoji());
        answer.getButtons().add(CommandInfo.No.getEmoji());
        parent.out(answer);
        return nextHandler;
    }
}
