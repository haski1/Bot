package handlers.quiz.instruction;

import core.instruction.Instruction;

import java.util.HashMap;

public class QuizInstructionsSet extends HashMap<String, Instruction>
{
    private Instruction defaultInstruction;
    public QuizInstructionsSet()
    {
        super();
        defaultInstruction = new Check();

        register(new Exit());
        register(defaultInstruction);
        register(new Question());
    }

    private void register(Instruction instruction)
    {
        super.put(instruction.getName(), instruction);
    }

    public Instruction getDefault()
    {
        return defaultInstruction;
    }
}
