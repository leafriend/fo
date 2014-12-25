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
    public void testSetName() {

        Class<?> generatedClass;
        byte[] bytecode;
        String name;

        // - - -

        name = "MainClass";

        bytecode = new ClassGenerator() //
                .setName(name)//
                .generateBytecode();

        generatedClass = defineClass(name, bytecode);

        assertThat(generatedClass.getName(), is(name));

        // - - -

        name = "foo.MainClass";

        bytecode = new ClassGenerator() //
                .setName(name)//
                .generateBytecode();

        generatedClass = defineClass(name, bytecode);

        assertThat(generatedClass.getName(), is(name));

    }

    public static Class<?> defineClass(String name, byte[] bytecode) {
        return new ByteArrayClassLoader().defineClass(name, bytecode);
    }

}
