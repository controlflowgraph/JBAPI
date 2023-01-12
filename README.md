# JBAPI
The Java Bytecode API (JBAPI) is a small wrapper around the [ASM](https://mvnrepository.com/artifact/org.ow2.asm/asm) library.
The wrapper focuses on an expressive and readable approach rather than high performance.
The main goal compared to the ASM library is the use of dedicated methods for instructions to increase
the usability.

```java
ClassBuilder test = ClassBuilder.builder()
        .name("Test");

test.method()
        .modifiers(Modifier.PUBLIC, Modifier.STATIC)
        .name("main")
        .parameters(String[].class)
        .code()
        .getStatic(System.class, "out")
        .constant("Hello World!")
        .call(type(PrintStream.class), "println", none(String.class))
        .vreturn();

byte[] generate = test.generate();
Files.write(Path.of("Test.class"), generate);
```