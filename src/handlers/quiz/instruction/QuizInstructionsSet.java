package handlers.quiz.instruction;

import core.instruction.IInstruction;
import core.set.BaseSet;
import handlers.basic.instructions.Start;

public class QuizInstructionsSet extends BaseSet<String, IInstruction>
{
    public QuizInstructionsSet()
    {
        super();
        register(new Start());
        register(new Exit());
        register(new Check());
        register(new Question());
    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
