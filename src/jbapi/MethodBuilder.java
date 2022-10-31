package jbapi;

public class MethodBuilder
{
    public static MethodBuilder builder()
    {
        return new MethodBuilder();
    }

    private String name;
    private String result;
    private Modifier[] modifiers;

    private MethodBuilder()
    {

    }

    public MethodBuilder result(String type)
    {
        this.result = type;
        return this;
    }

    public MethodBuilder name(String name)
    {
        this.name = name;
        return this;
    }

    public MethodBuilder modifiers(Modifier ... modifiers)
    {
        this.modifiers = modifiers;
        return this;
    }
}
