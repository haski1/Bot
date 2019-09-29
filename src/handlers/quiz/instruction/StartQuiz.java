package handlers.quiz.instruction;

import core.IO;
import core.data.Message;
import core.data.State;
import core.instruction.BaseInstruction;

public class StartQuiz extends BaseInstruction
{
    @Override
    public void execute(Message msg, IO handler)
    {
        msg.result = "Вы вошли в викторину!\nВыход: /exit";
        msg.user.state = State.Quiz;
        msg.done = true;
        handler.out(msg);
        msg.command = "question";
        handler.in(msg);
    }
}
