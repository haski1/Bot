package core.data;

public class ID
{
    private String name;
    private Platform platform;

    public ID(String name, Platform platform)
    {
        this.name = name;
        this.platform = platform;
    }

    public String getName()
    {
        return name;
    }

    public Platform getPlatform()
    {
        return platform;
    }

    @Override
    public int hashCode()
    {
        return (name + platform).hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof ID))
            return false;
        return ((ID) obj).name.equals(name) && ((ID) obj).platform.equals(platform);
    }
}
