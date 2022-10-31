package jbapi;

import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;

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

    public CodeBuilder ret()
    {
        return add("return");
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
            case "invoke-virtual" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String signature = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKEVIRTUAL, cls, name, signature);
            }
            default -> throw new RuntimeException("Unknown instruction '" + i.mnemonic() + "'!");
        }});
    }
}
