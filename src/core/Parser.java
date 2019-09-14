package core;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Parser implements IParser
{
    public Command parse(String input)
    {
        var params = input.split(" ");
        var command = params[0];
        var arguments = Arrays.copyOfRange(params, 1, Array.getLength(params));
        return new Command(command, arguments);
    }
}
