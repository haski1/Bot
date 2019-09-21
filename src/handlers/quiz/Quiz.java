package handlers.quiz;

import core.IIO;
import core.data.Message;
import core.instruction.IInstruction;
import core.set.ISet;
import handlers.quiz.instruction.QuizInstructionsSet;

public class Quiz implements IIO
{
    private ISet instructions;
    private IIO handler;

    public Quiz(IIO handler)
    {
        instructions = new QuizInstructionsSet();
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = (IInstruction)instructions.find(msg.instruction);
        if (instruction == null)
        {
            instruction = (IInstruction)instructions.find("check");
        }
        instruction.execute(msg);
        out(msg);
    }

    @Override
    public void out(Message msg)
    {
        handler.out(msg);
    }
}
