package core;

import architecture.IManager;

import java.io.*;

public class Manager implements IManager
{
    private BufferedReader reader;
    private String context;

    public Manager(){
        File file = new File("src\\data\\questions.txt");
        FileReader fileReader = null;
        try
        {
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        reader = new BufferedReader(fileReader);
    }

    public String get()
    {
        StringBuilder output = new StringBuilder();
        String line = new String();

        while(!line.contains("Answer"))
        {
            output.append(line);
            output.append("\n");
            try
            {
                line = reader.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        context = line.split(" ")[1];
        return output.toString();
    }

    public boolean check(String answer)
    {
        return context.equals(answer);
    }
}
