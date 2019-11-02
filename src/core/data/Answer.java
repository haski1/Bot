package core.data;

import java.util.ArrayList;
import java.util.List;

public class Answer
{
    private ID id;
    private String result;
    private ArrayList<String> buttons;

    public Answer(ID id, String result)
    {
        this.id = id;
        this.result = result;
        this.buttons = new ArrayList<>();
    }

    public String getResult()
    {
        return result;
    }

    public ID getId()
    {
        return id;
    }

    public List<String> getButtons()
    {
        return  buttons;
    }
}
