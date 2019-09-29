package handlers.basic.instructions;

import core.instruction.Instruction;
import handlers.chat.instructions.StartChat;
import handlers.quiz.instruction.Check;
import handlers.quiz.instruction.StartQuiz;

import java.util.HashMap;

public class InstructionsSet extends HashMap<String, Instruction>
{
    private Instruction defaultInstruction;

    public InstructionsSet()
    {
        super();
        defaultInstruction = new Help();

        register(new Start());
        register(new StartQuiz());
        register(new StartChat());
        register(defaultInstruction);

    }
    private void register(Instruction instruction)
    {
        this.put(instruction.getName(), instruction);
    }

    public Instruction getDefault()
    {
        return defaultInstruction;
    }
}
