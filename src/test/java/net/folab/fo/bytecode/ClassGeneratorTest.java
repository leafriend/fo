package net.folab.fo.bytecode;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import net.folab.fo.ast.AstVisitor;
import net.folab.fo.ast.AstWriter;

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

        AstVisitor av;
        Class<?> generatedClass;
        byte[] bytecode;
        String name;

        Object obj;

        // - - -

        name = "MainClass";
        av = new AstWriter(name);

        new ClassGenerator(name).accept(av);
        bytecode = av.toByteArray();

        generatedClass = defineClass(name, bytecode);
        assertThat(generatedClass.getName(), is(name));

        obj = generatedClass.newInstance();
        assertThat(obj, is(not(nullValue())));

        // - - -

        name = "foo.MainClass";
        av = new AstWriter(name);

        new ClassGenerator(name).accept(av);
        bytecode = av.toByteArray();

        generatedClass = defineClass(name, bytecode);

        assertThat(generatedClass.getName(), is(name));

        obj = generatedClass.newInstance();
        assertThat(obj, is(not(nullValue())));

    }

    public static Class<?> defineClass(String name, byte[] bytecode) {
        return new ByteArrayClassLoader().defineClass(name, bytecode);
    }

}
