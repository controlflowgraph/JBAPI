package jbapi;

public class ClassBuilder
{
    public static ClassBuilder builder()
    {
        return new ClassBuilder();
    }

    private int version;
    private String name;

    private ClassBuilder()
    {

    }

    public ClassBuilder version(int version)
    {
        this.version = version;
        return this;
    }

    public ClassBuilder name(String name)
    {
        this.name = name;
        return this;
    }
}
