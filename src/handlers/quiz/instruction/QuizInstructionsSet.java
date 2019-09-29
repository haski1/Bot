package handlers.quiz.instruction;

import core.instruction.Instruction;
import core.set.BaseSet;

public class QuizInstructionsSet extends BaseSet<String, Instruction>
{
    public QuizInstructionsSet()
    {
        super();
        register(new Exit());
        register(new Check());
        register(new Question());
    }

    public void register(Instruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
