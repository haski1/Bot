package handlers.chat.instructions;

import core.IIO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;

public class StartChat extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler)
    {
        msg.user.state = State.Chat;
        msg.result = "Вы вошли в анонимный чат\nДля поиска собеседника введите команду /search\n" +
                     "Чтобы выйти введите команду /exit";
        msg.done = true;
        handler.out(msg);
    }
}
