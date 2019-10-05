package core;

import core.data.Message;

public interface IO
{
    void in(Message msg);

    void out(Message msg);
}
