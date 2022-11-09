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
        return add("load-integer-constant", i);
    }

    public Label[] getLabels(int amount)
    {
        Label[] labels = new Label[amount];
        for (int i = 0; i < amount; i++)
        {
            labels[i] = getLabel();
        }
        return labels;
    }

    public Label getLabel()
    {
        return new Label();
    }

    public CodeBuilder label(Label label)
    {
        return add("define-label", label);
    }

    public CodeBuilder istore(int i)
    {
        return add("store-integer", i);
    }

    public CodeBuilder iload(int i)
    {
        return add("load-integer", i);
    }

    public CodeBuilder bipush(int i)
    {
        return add("push-byte-as-integer", i);
    }

    public CodeBuilder jmpge(Label label)
    {
        return add("jump-greater-equal", label);
    }

    public CodeBuilder jmp(Label label)
    {
        return add("jump", label);
    }

    public CodeBuilder iinc(int index, int constant)
    {
        return add("increment-integer", index, constant);
    }

    public CodeBuilder iadd()
    {
        return add("add-integer");
    }

    public CodeBuilder isub()
    {
        return add("sub-integer");
    }

    public CodeBuilder imul()
    {
        return add("mul-integer");
    }

    public CodeBuilder idiv()
    {
        return add("div-integer");
    }

    public CodeBuilder fadd()
    {
        return add("add-float");
    }

    public CodeBuilder fsub()
    {
        return add("sub-float");
    }

    public CodeBuilder fmul()
    {
        return add("mul-float");
    }

    public CodeBuilder fdiv()
    {
        return add("div-float");
    }

    public CodeBuilder ladd()
    {
        return add("add-long");
    }

    public CodeBuilder lsub()
    {
        return add("sub-long");
    }

    public CodeBuilder lmul()
    {
        return add("mul-long");
    }

    public CodeBuilder ldiv()
    {
        return add("div-long");
    }

    public CodeBuilder dadd()
    {
        return add("add-double");
    }

    public CodeBuilder dsub()
    {
        return add("sub-double");
    }

    public CodeBuilder dmul()
    {
        return add("mul-double");
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
            case "increment-integer" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                int constant = i.arguments().getAs(Integer.class, 1);
                method.visitIincInsn(index, constant);
            }
            case "getstatic" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitFieldInsn(GETSTATIC, cls, name, type);
            }
            case "jump-greater-equal" -> {
                Label label = i.arguments().getAs(Label.class, 0);
                method.visitJumpInsn(IF_ICMPGE, label.get());
            }
            case "jump" -> {
                Label label = i.arguments().getAs(Label.class, 0);
                method.visitJumpInsn(GOTO, label.get());
            }
            case "push-byte-as-integer" -> {
                int value = i.arguments().getAs(Integer.class, 0);
                method.visitIntInsn(BIPUSH, value);
            }
            case "load-string-constant" -> {
                String value = i.arguments().getAs(String.class, 0);
                method.visitLdcInsn(value);
            }
            case "load-integer-constant" -> {
                int value = i.arguments().getAs(Integer.class, 0);
                method.visitLdcInsn(value);
            }
            case "store-integer" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(ISTORE, index);
            }
            case "load-integer" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(ILOAD, index);
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
            case "add-integer" -> method.visitInsn(IADD);
            case "sub-integer" -> method.visitInsn(ISUB);
            case "mul-integer" -> method.visitInsn(IMUL);
            case "div-integer" -> method.visitInsn(IDIV);
            case "add-float" -> method.visitInsn(FADD);
            case "sub-float" -> method.visitInsn(FSUB);
            case "mul-float" -> method.visitInsn(FMUL);
            case "div-float" -> method.visitInsn(FDIV);
            case "add-long" -> method.visitInsn(LADD);
            case "sub-long" -> method.visitInsn(LSUB);
            case "mul-long" -> method.visitInsn(LMUL);
            case "div-long" -> method.visitInsn(LDIV);
            case "return" -> method.visitInsn(RETURN);
            case "aload" -> method.visitVarInsn(ALOAD, i.arguments().getAs(Integer.class, 0));
            case "define-label" -> {
                Label label = i.arguments().getAs(Label.class, 0);
                method.visitLabel(label.get());
            }
            default -> throw new RuntimeException("Unknown instruction '" + i.mnemonic() + "'!");
        }});
    }
}
