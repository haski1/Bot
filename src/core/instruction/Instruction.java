package core.instruction;

import core.IO;
import core.data.Message;

public interface Instruction
{
    public String getName();
    public void execute(Message msg, IO handler);
}
