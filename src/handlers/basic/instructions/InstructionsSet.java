package handlers.basic.instructions;

import core.command.BaseCommandsSet;
import handlers.basic.handlers.HandlersSet;
import handlers.calendar.instructions.StartCalendar;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseCommandsSet
{
    public InstructionsSet(HandlersSet loadedHandlers)
    {
        super(new Help());
        register(new Start(loadedHandlers));
        register(new StartQuiz());
        register(new StartChat());
        register(new StartCalendar());

    }
}
