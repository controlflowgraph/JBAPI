package jbapi;

public class FieldBuilder
{
    public static FieldBuilder builder()
    {
        return new FieldBuilder();
    }

    private String name;
    private String type;

    private FieldBuilder()
    {

    }

    public FieldBuilder name(String name)
    {
        this.name = name;
        return this;
    }

    public FieldBuilder type(String type)
    {
        this.type = type;
        return this;
    }
}
