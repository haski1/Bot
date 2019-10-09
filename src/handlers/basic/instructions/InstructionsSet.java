package handlers.basic.instructions;

import core.command.BaseCommandsSet;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseCommandsSet
{
    public InstructionsSet()
    {
        super();
        defaultCommand = new Help();
        register(defaultCommand);
        register(new Start());
        register(new StartQuiz());
        register(new StartChat());
    }
}
