package handlers.quiz.instruction;

import core.IO;
import core.data.Answer;
import core.data.Message;
import core.data.State;
import core.data.User;
import core.command.Command;
import handlers.quiz.data.QuizData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question implements Command
{
    private ArrayList<QuizData> questions = new ArrayList<>();
    private boolean error;

    public Question()
    {
        List<String> lines;
        try
        {
            lines = Files.readAllLines(Paths.get(System.getProperty("user.dir")
                    + "\\src\\handlers\\quiz\\data\\questions.txt"), StandardCharsets.UTF_8);
            for(String line: lines){
                questions.add(parseLine(line));
            }
        } catch (IOException e)
        {
            error = true;
            System.out.println("Quiz: questions isn\'t uploaded");
        }
    }

    public QuizData parseLine(String line)
    {
        var paths = line.split("/");
        return new QuizData(paths[0], paths[1]);
    }

    @Override
    public void execute(Message msg, User user, IO parent)
    {
        if (error)
        {
            var result = "Не удалось загрузить список вопросов\nВыход /exit";
            parent.out(new Answer(msg.getId(), result));
            return;
        }

        var question = questions.get(new Random().nextInt(questions.size()));
        user.setData(State.Quiz, question);
        var result = question.question;
        parent.out(new Answer(msg.getId(), result));
    }
}
