package core.data;

import core.command.Command;
import handlers.basic.instructions.Start;

public enum Module
{
    Basic("Наберите команду /start чтобы узнать больше!", CommandInfo.Start),
    Quiz("Пиши /startquiz чтобы проиграть в викторину!\n", CommandInfo.Quiz),
    Chat("Пиши /startchat зайти в анонимный чат!\n", CommandInfo.Chat),
    Calendar("Пиши /startcalendar чтобы зайти в календарь!", CommandInfo.Calendar);

    private String prompt;
    private CommandInfo cmd;

    Module(String prompt, CommandInfo cmd)
    {
        this.prompt = prompt;
        this.cmd = cmd;
    }

    public String getPrompt()
    {
        return this.prompt;
    }

    public CommandInfo getCommand()
    {
        return this.cmd;
    }
}
