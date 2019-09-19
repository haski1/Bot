import platforms.PlatformsSet;
import core.Data.Message;
import core.IIO;
import scripts.ScriptsSet;

public class Handler implements IIO
{
    public PlatformsSet platforms;
    private ScriptsSet scripts;

    public Handler()
    {
        platforms = new PlatformsSet(this);
        scripts = new ScriptsSet(this);
    }

    @Override
    public void in(Message msg)
    {
        var script = scripts.find(msg.user.state);
        script.in(msg);
    }

    @Override
    public void out(Message msg)
    {
        var platform = platforms.find(msg.user.platform);
        platform.out(msg);
    }
}
