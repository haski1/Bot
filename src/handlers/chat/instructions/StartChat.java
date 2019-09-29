package handlers.chat.instructions;

import core.IO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;

public class StartChat extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.user.state = State.Chat;
        msg.result = "Вы вошли в анонимный чат\nВыход: /exit";
        msg.done = true;
        handler.out(msg);
        msg.command = "search";
        handler.in(msg);
    }
}
