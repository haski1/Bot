package core.data;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class Answer
{
    private ID id;
    private String result;
    private KeyboardRow buttons;

    public Answer(ID id, String result)
    {
        this.id = id;
        this.result = result;
        this.buttons = new KeyboardRow();
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

    public KeyboardRow getButtons()
    {
        return  buttons;
    }
}
