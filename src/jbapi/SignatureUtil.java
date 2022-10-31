package jbapi;

import static jbapi.TypeUtil.arg;

public class SignatureUtil
{
    public static String some(Class<?> res, Class<?> ... args)
    {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        for (Class<?> arg : args)
        {
            builder.append(arg(arg));
        }
        builder.append(')');
        builder.append(arg(res));
        return builder.toString();
    }

    public static String none(Class<?> ... args)
    {
        return some(void.class, args);
    }

    private SignatureUtil()
    {

    }
}
