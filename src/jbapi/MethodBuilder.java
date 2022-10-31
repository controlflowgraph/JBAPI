package jbapi;

public class MethodBuilder
{
    public static MethodBuilder builder()
    {
        return new MethodBuilder();
    }

    private String name;

    private MethodBuilder()
    {

    }

    public MethodBuilder name(String name)
    {
        this.name = name;
        return this;
    }
}
