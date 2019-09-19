package platforms;

import core.Data.Message;
import core.IIO;

public class TelegramIO implements IIO
{
    private IIO handler;

    public TelegramIO(IIO handler)
    {
        this.handler = handler;
    }

    @Override
    public void in(Message msg)
    {

    }

    @Override
    public void out(Message msg)
    {

    }
}
