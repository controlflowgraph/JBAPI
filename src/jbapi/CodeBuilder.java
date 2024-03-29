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
        return callVirtual(cls, name, signature);
    }

    public CodeBuilder callVirtual(String cls, String name, String signature)
    {
        return add("invoke-virtual", cls, name, signature);
    }

    public CodeBuilder callInterface(String cls, String name, String signature)
    {
        return add("invoke-interface", cls, name, signature);
    }

    public CodeBuilder callStatic(String cls, String name, String signature)
    {
        return add("invoke-static", cls, name, signature);
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

    public CodeBuilder constant(double d)
    {
        return add("load-double-constant", d);
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

    public CodeBuilder lstore(int i)
    {
        return add("store-long", i);
    }

    public CodeBuilder lload(int i)
    {
        return add("load-long", i);
    }

    public CodeBuilder fstore(int i)
    {
        return add("store-float", i);
    }

    public CodeBuilder fload(int i)
    {
        return add("load-float", i);
    }

    public CodeBuilder dstore(int i)
    {
        return add("store-double", i);
    }

    public CodeBuilder dload(int i)
    {
        return add("load-double", i);
    }

    public CodeBuilder bipush(int i)
    {
        return add("push-byte-as-integer", i);
    }

    public CodeBuilder ijmpge(Label label)
    {
        return add("integer-jump-greater-equal", label);
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

    public CodeBuilder ddiv()
    {
        return add("div-double");
    }

    public CodeBuilder newByteArray()
    {
        return add("new-byte-array");
    }

    public CodeBuilder newShortArray()
    {
        return add("new-short-array");
    }

    public CodeBuilder newIntArray()
    {
        return add("new-int-array");
    }

    public CodeBuilder newLongArray()
    {
        return add("new-long-array");
    }

    public CodeBuilder newFloatArray()
    {
        return add("new-float-array");
    }

    public CodeBuilder newDoubleArray()
    {
        return add("new-double-array");
    }

    public CodeBuilder newCharArray()
    {
        return add("new-char-array");
    }

    public CodeBuilder newBooleanArray()
    {
        return add("new-boolean-array");
    }

    public CodeBuilder newObjectArray(String name)
    {
        return add("new-object-array", name);
    }

    public CodeBuilder baload()
    {
        return add("baload");
    }

    public CodeBuilder saload()
    {
        return add("saload");
    }

    public CodeBuilder iaload()
    {
        return add("iaload");
    }

    public CodeBuilder laload()
    {
        return add("laload");
    }

    public CodeBuilder faload()
    {
        return add("faload");
    }

    public CodeBuilder daload()
    {
        return add("daload");
    }

    public CodeBuilder caload()
    {
        return add("caload");
    }

    public CodeBuilder aaload()
    {
        return add("aaload");
    }

    public CodeBuilder bastore()
    {
        return add("bastore");
    }

    public CodeBuilder sastore()
    {
        return add("sastore");
    }

    public CodeBuilder iastore()
    {
        return add("iastore");
    }

    public CodeBuilder lastore()
    {
        return add("lastore");
    }

    public CodeBuilder fastore()
    {
        return add("fastore");
    }

    public CodeBuilder dastore()
    {
        return add("dastore");
    }

    public CodeBuilder castore()
    {
        return add("castore");
    }

    public CodeBuilder aastore()
    {
        return add("aastore");
    }

    public CodeBuilder astore(int i)
    {
        return add("astore", i);
    }

    public CodeBuilder line(int i)
    {
        return add("line", i);
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
            case "integer-jump-greater-equal" -> {
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
            case "load-double-constant" -> {
                double value = i.arguments().getAs(Double.class, 0);
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
            case "store-long" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(LSTORE, index);
            }
            case "load-long" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(LLOAD, index);
            }
            case "store-float" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(FSTORE, index);
            }
            case "load-float" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(FLOAD, index);
            }
            case "store-double" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(DSTORE, index);
            }
            case "load-double" -> {
                int index = i.arguments().getAs(Integer.class, 0);
                method.visitVarInsn(DLOAD, index);
            }
            case "invoke-virtual" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String signature = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKEVIRTUAL, cls, name, signature, false);
            }
            case "invoke-interface" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKEINTERFACE, cls, name, type, true);
            }
            case "invoke-static" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKESTATIC, cls, name, type, false);
            }
            case "invoke-special" -> {
                String cls = i.arguments().getAs(String.class, 0);
                String name = i.arguments().getAs(String.class, 1);
                String type = i.arguments().getAs(String.class, 2);
                method.visitMethodInsn(INVOKESPECIAL, cls, name, type, false);
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
            case "add-double" -> method.visitInsn(DADD);
            case "sub-double" -> method.visitInsn(DSUB);
            case "mul-double" -> method.visitInsn(DMUL);
            case "div-double" -> method.visitInsn(DDIV);
            case "new-byte-array" -> method.visitIntInsn(NEWARRAY, T_BYTE);
            case "new-short-array" -> method.visitIntInsn(NEWARRAY, T_SHORT);
            case "new-int-array" -> method.visitIntInsn(NEWARRAY, T_INT);
            case "new-long-array" -> method.visitIntInsn(NEWARRAY, T_LONG);
            case "new-float-array" -> method.visitIntInsn(NEWARRAY, T_FLOAT);
            case "new-double-array" -> method.visitIntInsn(NEWARRAY, T_DOUBLE);
            case "new-char-array" -> method.visitIntInsn(NEWARRAY, T_CHAR);
            case "new-boolean-array" -> method.visitIntInsn(NEWARRAY, T_BOOLEAN);
            case "new-object-array" -> method.visitTypeInsn(ANEWARRAY, i.arguments().getAs(String.class, 0));
            case "return" -> method.visitInsn(RETURN);
            case "aload" -> method.visitVarInsn(ALOAD, i.arguments().getAs(Integer.class, 0));
            case "astore" -> method.visitVarInsn(ASTORE, i.arguments().getAs(Integer.class, 0));
            case "bastore" -> method.visitInsn(BASTORE);
            case "sastore" -> method.visitInsn(SASTORE);
            case "iastore" -> method.visitInsn(IASTORE);
            case "lastore" -> method.visitInsn(LASTORE);
            case "fastore" -> method.visitInsn(FASTORE);
            case "dastore" -> method.visitInsn(DASTORE);
            case "castore" -> method.visitInsn(CASTORE);
            case "aastore" -> method.visitInsn(AASTORE);
            case "baload" -> method.visitInsn(BALOAD);
            case "saload" -> method.visitInsn(SALOAD);
            case "iaload" -> method.visitInsn(IALOAD);
            case "laload" -> method.visitInsn(LALOAD);
            case "faload" -> method.visitInsn(FALOAD);
            case "daload" -> method.visitInsn(DALOAD);
            case "caload" -> method.visitInsn(CALOAD);
            case "aaload" -> method.visitInsn(AALOAD);
            case "define-label" -> {
                Label label = i.arguments().getAs(Label.class, 0);
                method.visitLabel(label.get());
            }
            case "line" -> {
                int line = i.arguments().getAs(Integer.class, 0);
                org.objectweb.asm.Label l = new org.objectweb.asm.Label();
                method.visitLabel(l);
                method.visitLineNumber(line, l);
            }
            default -> throw new RuntimeException("Unknown instruction '" + i.mnemonic() + "'!");
        }});
    }
}
