package jbapi;

public class ClassBuilder
{
    public static ClassBuilder builder()
    {
        return new ClassBuilder();
    }

    private String name;

    private ClassBuilder()
    {

    }

    public ClassBuilder name(String name)
    {
        this.name = name;
        return this;
    }
}
