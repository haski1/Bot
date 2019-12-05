package handlers.basic.instructions;

import core.command.BaseCommandsSet;
import core.data.Module;
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
        if (loadedHandlers.containsKey(Module.Quiz))
    {
        register(new StartQuiz());
    }
        if (loadedHandlers.containsKey(Module.Chat))
        {
            register(new StartChat());
        }
        if (loadedHandlers.containsKey(Module.Calendar))
        {
            register(new StartCalendar());
        }
    }
}
