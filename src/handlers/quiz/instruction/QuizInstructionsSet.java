package handlers.quiz.instruction;

import core.command.BaseCommandsSet;

public class QuizInstructionsSet extends BaseCommandsSet
{
    public QuizInstructionsSet()
    {
        super();
        defaultCommand = new Check();
        register(defaultCommand);
        register(new Exit());
        register(new Question());
    }
}
