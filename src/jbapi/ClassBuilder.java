package jbapi;

import java.util.ArrayList;
import java.util.List;

public class ClassBuilder
{
    public static ClassBuilder builder()
    {
        return new ClassBuilder();
    }

    private int version;
    private String name;
    private String extending;
    private Modifier[] modifiers;
    private String[] interfaces;
    private final List<FieldBuilder> fields = new ArrayList<>();
    private final List<MethodBuilder> methods = new ArrayList<>();

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

    public ClassBuilder extending(String name)
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

    public FieldBuilder field()
    {
        return FieldBuilder.builder();
    }

    public MethodBuilder method()
    {
        MethodBuilder builder = MethodBuilder.builder();
        this.methods.add(builder);
        return builder;
    }
}
