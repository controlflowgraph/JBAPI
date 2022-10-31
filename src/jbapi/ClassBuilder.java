package jbapi;

public class ClassBuilder
{
    public static ClassBuilder builder()
    {
        return new ClassBuilder();
    }

    private int version;
    private String name;
    private Modifier[] modifiers;
    private String[] interfaces;

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

    public ClassBuilder modifiers(Modifier ... modifiers)
    {
        this.modifiers = modifiers;
        return this;
    }

    public ClassBuilder interfaces(String ... name)
    {
        this.interfaces = name;
        return this;
    }
}
