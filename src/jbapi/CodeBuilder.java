package jbapi;

import java.util.ArrayList;
import java.util.List;

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
}
