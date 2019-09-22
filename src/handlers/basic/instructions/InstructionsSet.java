package handlers.basic.instructions;

import core.IIO;
import core.instruction.IInstruction;
import core.set.BaseSet;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseSet<String, IInstruction>
{
    public InstructionsSet()
    {
        super();
        register(new Start());
        register(new StartQuiz());
        register(new StartChat());
        register(new Help());

    }

    public void register(IInstruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
