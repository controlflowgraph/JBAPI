package jbapi;

import java.util.ArrayList;
import java.util.List;

public class MethodBuilder
{
    public static MethodBuilder builder()
    {
        return new MethodBuilder();
    }

    private String name;
    private String result;
    private Modifier[] modifiers;
    private final List<String> parameters = new ArrayList<>();
    private CodeBuilder code;

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

    public MethodBuilder parameter(String cls)
    {
        this.parameters.add(cls);
        return this;
    }

    public CodeBuilder code()
    {
        return this.code = CodeBuilder.builder();
    }
}
