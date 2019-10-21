package handlers.quiz.instruction;

import core.IO;
import core.command.Command;
import core.data.Answer;
import core.data.Message;
import core.data.Module;
import core.data.User;
import handlers.quiz.data.QuizData;

import java.util.List;
import java.util.Random;

public class Question implements Command
{
    private List<QuizData> questions;

    public Question(List<QuizData> questions)
    {
        this.questions = questions;
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        var question = questions.get(new Random().nextInt(questions.size()));
        user.setData(Module.Quiz, question);
        parent.out(new Answer(msg.getId(), question.question));
    }
}
