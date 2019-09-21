package handlers.basic.instructions;

import core.IIO;
import core.instruction.IInstruction;
import core.set.BaseSet;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseSet<String, IInstruction>
{
    public InstructionsSet()
    {
        super();
        register(new Start());
        register(new StartQuiz());
        register(new Help());
        register(new List());

    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
