package net.folab.fo.bytecode;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClassGeneratorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetName() throws InstantiationException, IllegalAccessException {

        Class<?> generatedClass;
        byte[] bytecode;
        String name;

        Object obj;

        // - - -

        name = "MainClass";

        bytecode = new ClassGenerator() //
                .setName(name)//
                .generateBytecode();

        generatedClass = defineClass(name, bytecode);
        assertThat(generatedClass.getName(), is(name));

        obj = generatedClass.newInstance();
        assertThat(obj, is(not(nullValue())));

        // - - -

        name = "foo.MainClass";

        bytecode = new ClassGenerator() //
                .setName(name)//
                .generateBytecode();

        generatedClass = defineClass(name, bytecode);

        assertThat(generatedClass.getName(), is(name));

        obj = generatedClass.newInstance();
        assertThat(obj, is(not(nullValue())));

    }

    public static Class<?> defineClass(String name, byte[] bytecode) {
        return new ByteArrayClassLoader().defineClass(name, bytecode);
    }

}
