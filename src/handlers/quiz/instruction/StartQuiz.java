package handlers.quiz.instruction;

import core.IIO;
import core.data.Message;
import core.instruction.BaseInstruction;

public class StartQuiz extends BaseInstruction
{
    @Override
    public void execute(Message msg, IIO handler)
    {
        msg.result = "Вы вошли в викторину\nДля получения вопроса пиши команду" +
                " /question\nДля выхода пиши команду /exit";
        msg.user.state = "quiz";
        msg.done = true;
        handler.out(msg);
    }
}
