package jbapi;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

import static jbapi.TypeUtil.arg;

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

    public MethodBuilder parameters(Class<?> ... cls)
    {
        String[] args = new String[cls.length];
        for (int i = 0; i < cls.length; i++)
        {
            args[i] = arg(cls[i]);
        }
        return parameters(args);
    }

    public MethodBuilder parameters(String ... args)
    {
        for (String arg : args)
        {
            parameter(arg);
        }
        return this;
    }

    public MethodBuilder parameter(Class<?> cls)
    {
        return parameter(arg(cls));
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

    public void generate(ClassWriter writer)
    {
        int mod = Modifier.merge(this.modifiers);
        String reduce = String.join("", this.parameters);
        MethodVisitor method = writer.visitMethod(mod, this.name, "(" + reduce + ")" + this.result, null, null);
        this.code.generate(method);
        method.visitMaxs(0, 0);
        method.visitEnd();
    }
}
