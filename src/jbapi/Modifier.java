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
}
