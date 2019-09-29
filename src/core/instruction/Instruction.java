package core.instruction;

import core.IO;
import core.data.Message;

public interface Instruction
{
    String getName();
    void execute(Message msg, IO handler);
}
