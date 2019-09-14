package core;

import architecture.IManager;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Manager implements IManager
{
    private FileReader reader;
    private String context;

    public Manager()
    {
        try
        {

        }
    }

    public String get() {
        return null;
    }

    public boolean check(String answer) {
        return false;
    }
}
