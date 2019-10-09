package core.data;

public class Answer
{
    private ID id;
    private String result;

    public Answer(ID id, String result)
    {
        this.id = id;
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public ID getId()
    {
        return id;
    }
}
