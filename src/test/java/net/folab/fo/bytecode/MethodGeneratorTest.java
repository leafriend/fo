package net.folab.fo.bytecode;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MethodGeneratorTest {

    private static final String CLASS_NAME = "foo";

    private ClassGenerator cg;

    private MethodGenerator mg;

    @Before
    public void setUp() throws Exception {
        cg = new ClassGenerator().setName(CLASS_NAME);
        mg = cg.addMethod();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetName() throws SecurityException, NoSuchMethodException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Class<?> generatedClass;
        Method method;

        // - - -

        mg.setName("bar");

        generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());

        method = generatedClass.getDeclaredMethod("bar");
        assertThat(method, is(not(nullValue())));
        assertThat(method.getReturnType(), typeCompatibleWith(void.class));

    }

    @Test
    public void testSetReturnType_void() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.VOID);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(void.class));

    }

    @Test
    public void testSetReturnType_boolean() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.BOOLEAN);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(boolean.class));

        boolean b = (Boolean) method.invoke(generatedClass.newInstance());
        assertThat(b, is(false));

    }

    @Test
    public void testSetReturnType_byte() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.BYTE);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(byte.class));

        byte b = (Byte) method.invoke(generatedClass.newInstance());
        assertThat(b, is((byte) 0));

    }

    @Test
    public void testSetReturnType_char() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.CHAR);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(char.class));

        char c = (Character) method.invoke(generatedClass.newInstance());
        assertThat(c, is((char) 0));

    }

    @Test
    public void testSetReturnType_short() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.SHORT);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(short.class));

        short S = (Short) method.invoke(generatedClass.newInstance());
        assertThat(S, is((short) 0));

    }

    @Test
    public void testSetReturnType_int() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.INT);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(int.class));

        int i = (Integer) method.invoke(generatedClass.newInstance());
        assertThat(i, is(0));

    }

    @Test
    public void testSetReturnType_long() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.LONG);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(long.class));

        long l = (Long) method.invoke(generatedClass.newInstance());
        assertThat(l, is(0l));

    }

    @Test
    public void testSetReturnType_float() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.FLOAT);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(float.class));

        float f = (Float) method.invoke(generatedClass.newInstance());
        assertThat(f, is(0.0f));

    }

    @Test
    public void testSetReturnType_double() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(JavaType.DOUBLE);

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(double.class));

        double d = (Double) method.invoke(generatedClass.newInstance());
        assertThat(d, is(0.0d));

    }

    @Test
    public void testSetReturnType_Object() throws SecurityException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        mg.setName("bar").setReturnType(new JavaType("java/lang/Object"));

        Class<?> generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());
        Method method = generatedClass.getDeclaredMethod("bar");

        assertThat(method.getReturnType(), typeCompatibleWith(Object.class));

        Object o = method.invoke(generatedClass.newInstance());
        assertThat(o, is(nullValue()));

    }

    public static Class<?> defineClass(String name, byte[] bytecode) {
        return new ByteArrayClassLoader().defineClass(name, bytecode);
    }

}
