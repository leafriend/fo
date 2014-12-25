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
    public void testSetName() throws SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> generatedClass;
        Method method;

        // - - -

        mg.setName("bar");

        generatedClass = defineClass(CLASS_NAME, cg.generateBytecode());

        method = generatedClass.getDeclaredMethod("bar");
        assertThat(method, is(not(nullValue())));
        assertThat(method.getReturnType(), typeCompatibleWith(void.class));

    }

    public static Class<?> defineClass(String name, byte[] bytecode) {
        return new ByteArrayClassLoader().defineClass(name, bytecode);
    }

}
