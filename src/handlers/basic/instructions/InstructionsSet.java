package handlers.basic.instructions;

import core.instruction.Instruction;
import core.set.BaseSet;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.StartQuiz;

public class InstructionsSet extends BaseSet<String, Instruction>
{
    public InstructionsSet()
    {
        super();
        register(new Start());
        register(new StartQuiz());
        register(new StartChat());
        register(new Help());

    }

    public void register(Instruction instruction)
    {
        super.register(instruction.getName(), instruction);
    }
}
