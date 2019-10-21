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

    public void addButton(String label)
    {
        buttons.add(label);
    }

    public void addButtons(List<String> labels)
    {
        buttons.addAll(labels);
    }

    public ArrayList<String> getButtons()
    {
        return  buttons;
    }
}
