package core;

import core.data.Answer;
import core.data.Message;

public interface IO
{
    void in(Message msg);

    void out(Answer ans);
}
