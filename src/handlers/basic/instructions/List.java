package handlers.basic.instructions;

import core.data.Message;
import core.instruction.BaseInstruction;

public class List extends BaseInstruction
{
    @Override
    public void execute(Message msg)
    {
        msg.result = "Quiz\nAlchemy";
    }
}
