package handlers.basic.instructions;

import core.command.BaseCommandsSet;
import handlers.basic.handlers.HandlersSet;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseCommandsSet
{
    public InstructionsSet(HandlersSet loadedHandlers)
    {
        super();
        defaultCommand = new Help();
        register(defaultCommand);
        register(new Start(loadedHandlers));
        register(new StartQuiz());
        register(new StartChat());
    }
}
