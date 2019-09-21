package handlers.quiz.instruction;

import core.instruction.IInstruction;
import core.set.BaseSet;

import java.io.IOException;

public class QuizInstructionsSet extends BaseSet<String, IInstruction>
{
    public QuizInstructionsSet()
    {
        super();
        register(new StartQuiz());
        register(new Exit());
        register(new Check());
        register(new Question());
    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
