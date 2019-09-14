package core;

import architecture.ICommand;
import architecture.IParser;

import java.util.Scanner;

public class Reader
{
    private IParser parser;
    private Scanner scanner;

    public Reader(IParser parser)
    {
        this.parser = parser;
        scanner = new Scanner(System.in);
    }

    public ICommand nextCommand()
    {
        String input = scanner.nextLine();
        return parser.parse(input);
    }
}
