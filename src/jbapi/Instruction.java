package jbapi;

public record Instruction(String mnemonic, Arguments arguments)
{
    public Instruction(String mnemonic, Object ... args)
    {
        this(mnemonic, new Arguments(args));
    }
    public Instruction(String mnemonic)
    {
        this(mnemonic, (Arguments) null);
    }
}