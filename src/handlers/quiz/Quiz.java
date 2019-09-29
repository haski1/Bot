package handlers.quiz;

import core.IO;
import core.data.Message;
import core.instruction.Instruction;
import core.set.Set;
import handlers.quiz.instruction.QuizInstructionsSet;

public class Quiz implements IO
{
    private Set instructions;
    private IO handler;

    public Quiz(IO handler)
    {
        instructions = new QuizInstructionsSet();
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = (Instruction)instructions.find(msg.command);
        if (instruction == null && msg.text != null)
        {
            instruction = (Instruction)instructions.find("check");
        }
        instruction.execute(msg, this);
    }

    @Override
    public void out(Message msg)
    {
        handler.out(msg);
    }
}
