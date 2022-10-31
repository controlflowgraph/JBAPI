package jbapi;

import org.objectweb.asm.ClassWriter;

import java.util.ArrayList;
import java.util.List;

public class ClassBuilder
{
    public static ClassBuilder builder()
    {
        return new ClassBuilder();
    }

    private int version = 63;
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
        this.extending = name;
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
        FieldBuilder builder = FieldBuilder.builder();
        this.fields.add(builder);
        return builder;
    }

    public MethodBuilder method()
    {
        MethodBuilder builder = MethodBuilder.builder();
        this.methods.add(builder);
        return builder;
    }

    public MethodBuilder constructor()
    {
        MethodBuilder builder = MethodBuilder.builder()
                .name("<init>")
                .result("V");
        this.methods.add(builder);
        return builder;
    }

    public byte[] generate()
    {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS);
        int mod = Modifier.merge(this.modifiers) | 32;
        writer.visit(this.version, mod, this.name, null, this.extending, this.interfaces);
        this.fields.forEach(f -> f.generate(writer));
        this.methods.forEach(m -> m.generate(writer));
        return writer.toByteArray();
    }
}
