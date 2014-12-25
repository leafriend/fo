package net.folab.fo.bytecode;

public class ByteArrayClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }

}
