package core;

import core.data.Message;

public interface IO
{
    public void in(Message msg);

    public void out(Message msg);
}
