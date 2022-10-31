package jbapi;

public class Arguments
{
    private final Object[] args;

    public Arguments(Object[] args)
    {
        this.args = args;
    }

    public <T> T getAs(Class<T> cls, int index)
    {
        return cls.cast(get(index));
    }

    public Object get(int index)
    {
        return this.args[index];
    }
}