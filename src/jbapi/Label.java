package jbapi;

public class Label
{
    private final org.objectweb.asm.Label l = new org.objectweb.asm.Label();

    org.objectweb.asm.Label get()
    {
        return this.l;
    }
}
