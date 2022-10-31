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
