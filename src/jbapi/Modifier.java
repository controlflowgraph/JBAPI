package jbapi;

import static org.objectweb.asm.Opcodes.*;

public enum Modifier
{
    PUBLIC(ACC_PUBLIC),
    PRIVATE(ACC_PRIVATE),
    PROTECTED(ACC_PROTECTED),
    STATIC(ACC_STATIC),
    FINAL(ACC_FINAL),
    ;

    private final int value;

    Modifier(int value)
    {
        this.value = value;
    }

    public static int merge(Modifier ... modifiers)
    {
        if(modifiers == null)
            return 0;

        int value = 0;
        for (Modifier modifier : modifiers)
        {
            value |= modifier.value;
        }
        return value;
    }
}
