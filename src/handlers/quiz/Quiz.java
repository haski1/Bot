package handlers.quiz;

import core.IO;
import core.data.Message;
import handlers.quiz.instruction.QuizInstructionsSet;

public class Quiz implements IO
{
    private QuizInstructionsSet instructions;
    private IO parentHandler;

    public Quiz(IO handler)
    {
        instructions = new QuizInstructionsSet();
        this.parentHandler = handler;
    }

    @Override
    public void in(Message msg)
    {
        var instruction = instructions.get(msg.command);
        if (instruction == null && msg.text != null)
        {
            instruction = instructions.getDefault();
        }
        instruction.execute(msg, this);
    }

    @Override
    public void out(Message msg)
    {
        parentHandler.out(msg);
    }
}
