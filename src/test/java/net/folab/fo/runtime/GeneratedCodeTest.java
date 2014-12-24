package net.folab.fo.runtime;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.Before;

public class GeneratedCodeTest {

    private Class<?> genClass;

    @Before
    public void setUp() throws ClassNotFoundException {
        genClass = Class.forName("net.folab.fo.runtime._");
    }

    @Test
    public void test() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {

        test("_a", "1");
        test("_b", "2");
        test("_c", "3");
        test("_d", "4");
        test("_e", "5");

    }

    public void test(String name, String string) throws NoSuchFieldException,
            IllegalAccessException {
        Field field = genClass.getField(name);
        Evaluable<?> value = (Evaluable<?>) field.get(null);
        assertThat(value.evaluate().toString(), is(string));
    }

}
