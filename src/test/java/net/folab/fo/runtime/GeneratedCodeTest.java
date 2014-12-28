package net.folab.fo.runtime;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.lang.reflect.Field;

import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang.Integer;

import org.junit.Test;

public class GeneratedCodeTest {

    private Class<?> genClass;

    public void setup(String className) throws ClassNotFoundException {
        ClassLoader classLoader = new ClassLoader(getClass().getClassLoader()) {
        };
        genClass = classLoader.loadClass(className);
    }

    @Test
    public void test__() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        setup("net.folab.fo.runtime._");
        test();
    }

    public void test() throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {

        test("_a", Integer.valueOf(1));
        test("_b", Integer.valueOf(2));
        test("_c", Integer.valueOf(3));
        test("_d", Integer.valueOf(4));
        test("_e", Integer.valueOf(5));
        test("_f", Boolean.FALSE);
        test("_t", Boolean.TRUE);
        test("_nt", Boolean.FALSE);

    }

    public <T extends Evaluable<T>> void test(String name, Evaluable<T> expected)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = genClass.getField(name);
        @SuppressWarnings("unchecked")
        Evaluable<T> value = (Evaluable<T>) field.get(null);
        assertThat(value.evaluate(), is(expected));
    }

}
