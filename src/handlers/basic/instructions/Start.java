package handlers.basic.instructions;

import core.IO;
import core.command.Command;
import core.data.*;
import core.data.Module;
import handlers.basic.handlers.HandlersSet;

import java.util.ArrayList;

public class Start implements Command
{
    private HandlersSet loadedHandlers;

    public Start(HandlersSet loadedHandlers)
    {
        this.loadedHandlers = loadedHandlers;
    }

    public void execute(Message msg, User user, IO parent)
    {
        var str = new StringBuilder();
        var buttons = new ArrayList<String>();
        str.append("Привет! \nЭто iwanttoseebot\n");
        for (var module: Module.values())
        {
            if (loadedHandlers.containsKey(module))
            {
                str.append(module.getPrompt());
                buttons.add(module.getButton());
            }
        }
        var answer = new Answer(user.getId(), str.toString());
        answer.addButtons(buttons);
        parent.out(answer);
    }
}