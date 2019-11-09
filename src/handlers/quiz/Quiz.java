package handlers.quiz;

import core.IO;
import core.data.Answer;
import core.data.ID;
import core.data.Message;
import core.data.User;
import handlers.quiz.data.QuizData;
import handlers.quiz.instruction.QuizInstructionsSet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Quiz implements IO
{
    private QuizInstructionsSet instructions;
    private ConcurrentHashMap<ID, User> users;
    private IO parentHandler;

    public Quiz(IO handler, ConcurrentHashMap<ID, User> users, Path questionPath) throws IOException
    {
        var questions = loadQuestions(questionPath);
        instructions = new QuizInstructionsSet(questions);
        this.parentHandler = handler;
        this.users = users;
    }

    private List<QuizData> loadQuestions(Path path) throws IOException
    {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        List<QuizData> questions = new ArrayList<>();
        for(String line: lines)
        {
            questions.add(parseLine(line));
        }
        return questions;
    }

    private QuizData parseLine(String line)
    {
        var couple = line.split("/");
        return new QuizData(couple[0], couple[1]);
    }

    @Override
    public void in(Message msg)
    {
        var user = users.get(msg.getId());
        if (instructions.containsKey(msg.getCommand()))
        {
            instructions.get(msg.getCommand()).execute(msg, user, this);
        }
        else
        {
            if (msg.getCommand() == null)
            {
                instructions.getDefault().execute(msg, user, this);
            }
            else
            {
                out(new Answer(msg.getId(), "Неизвестная команда"));
            }
        }
    }

    @Override
    public void out(Answer ans)
    {
        parentHandler.out(ans);
    }
}
