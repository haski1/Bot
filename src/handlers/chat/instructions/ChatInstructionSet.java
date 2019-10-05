package handlers.chat.instructions;

import core.instruction.Instruction;

import java.util.HashMap;

public class ChatInstructionSet extends HashMap<String, Instruction>
{
    private Instruction defaultInstruction;

    public ChatInstructionSet()
    {
        super();
        defaultInstruction = new Dialog();

        register(new Search());
        register(defaultInstruction);
        register(new Exit());
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
