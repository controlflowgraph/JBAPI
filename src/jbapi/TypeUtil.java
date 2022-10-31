package jbapi;

import java.util.Map;

public class TypeUtil
{
    private static final Map<String, String> MAPPING = Map.of(
            "char", "C",
            "float", "F",
            "double", "D",
            "byte", "B",
            "short", "S",
            "int", "I",
            "long", "J",
            "void", "V",
            "boolean", "Z"
    );

    public static String type(Class<?> cls)
    {
        return clean(name(cls));
    }

    private static String clean(String name)
    {
        return MAPPING.getOrDefault(name, name);
    }

    private static String name(Class<?> cls)
    {
        return cls.getCanonicalName().replace(".", "/");
    }

    public static String arg(Class<?> cls)
    {
        String name = name(cls);
        int count = countArrays(name);
        return wrapped(name, count);
    }

    private static String wrapped(String name, int count)
    {
        String cls = name.substring(0, name.length() - count * 2);
        return "[".repeat(count) + MAPPING.getOrDefault(cls, "L" + cls + ";");
    }

    private static int countArrays(String converted)
    {
        return converted
                .replaceAll("[^\\[]", "")
                .length();
    }

    private TypeUtil()
    {

    }
}
