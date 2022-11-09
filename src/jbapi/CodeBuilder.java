package jbapi;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

import static jbapi.TypeUtil.arg;
import static jbapi.TypeUtil.type;
import static org.objectweb.asm.Opcodes.*;

public class CodeBuilder
{
    public static CodeBuilder builder()
    {
        return new CodeBuilder();
    }

    private final List<Instruction> instructions = new ArrayList<>();

    private CodeBuilder()
    {

    }

    public CodeBuilder getStatic(Class<?> cls, String field)
    {
        String t;
        try
        {
            t = arg(cls.getDeclaredField(field).getType());
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException(e);
        }
        return getStatic(type(cls), field, t);
    }

    public CodeBuilder getStatic(String cls, String field, String type)
    {
        return add("getstatic", cls, field, type);
    }

    public CodeBuilder constant(String value)
    {
        return add("load-string-constant", value);
    }

    public CodeBuilder call(String cls, String name, String signature)
    {
        return add("invoke-virtual", cls, name, signature);
    }

    public CodeBuilder vreturn()
    {
        return add("return");
    }

    public CodeBuilder aload(int index)
    {
        add("aload", index);
        return this;
    }

    public CodeBuilder special(String cls, String name, String signature)
    {
        return add("invoke-special", cls, name, signature);
    }

    public CodeBuilder constant(int i)
    {
        this.instructions.add(new Instruction("load-integer-constant", i));
        return this;
    }

    public Label[] labels(int amount)
    {
        Label[] labels = new Label[amount];
        for (int i = 0; i < amount; i++)
        {
            labels[i] = label();
        }
        return labels;
    }

    public Label label()
    {
        return new Label();
    }

    private CodeBuilder add(String mnemonic)
    {
        this.instructions.add(new Instruction(mnemonic));
        return this;
    }

    private CodeBuilder add(String mnemonic, Object... values)
    {
        this.instructions.add(new Instruction(mnemonic, values));
        return this;
    }

    public void generate(MethodVisitor method)
    {
        method.visitCode();
        this.instructions.forEach(i -> { switch (i.mnemonic())
        {
            case "getstatic" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitFieldInsn(GETSTATIC, cls, name, type);
            }
            case "load-string-constant" -> {
                String value = i.arguments().getAs(String.class, 0);
                method.visitLdcInsn(value);
            }
            case "load-integer-constant" -> {
                int value = i.arguments().getAs(Integer.class, 0);
                method.visitLdcInsn(value);
            }
            case "invoke-virtual" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String signature = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKEVIRTUAL, cls, name, signature);
            }
            case "invoke-special" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKESPECIAL, cls, name, type);
            }
            case "return" -> method.visitInsn(RETURN);
            case "aload" -> method.visitVarInsn(ALOAD, i.arguments().getAs(Integer.class, 0));
            default -> throw new RuntimeException("Unknown instruction '" + i.mnemonic() + "'!");
        }});
    }
}
