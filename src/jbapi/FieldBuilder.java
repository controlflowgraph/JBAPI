package jbapi;

import org.objectweb.asm.ClassWriter;

public class FieldBuilder
{
    public static FieldBuilder builder()
    {
        return new FieldBuilder();
    }

    private String name;
    private String type;
    private Modifier[] modifiers;

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

    public FieldBuilder modifiers(Modifier ... modifiers)
    {
        this.modifiers = modifiers;
        return this;
    }

    public void generate(ClassWriter writer)
    {
        int mod = Modifier.merge(this.modifiers);
        writer.visitField(mod, this.name, this.type, null, null)
                .visitEnd();
    }
}
