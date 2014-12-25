package net.folab.fo.runtime._fo._lang;

import static net.folab.fo.runtime._fo._lang.Boolean.*;
import static net.folab.fo.runtime._fo._lang.Integer.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime.FunctionBinding;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BooleanTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_Boolean() {

        Evaluable<Function<Boolean, Function<Boolean, Boolean>>> f = FALSE
                .choose();
        Evaluable<Function<Boolean, Function<Boolean, Boolean>>> t = TRUE
                .choose();

        // false.choose
        // ->
        // FALSE.choose()

        // false.choose false
        // ->
        // FALSE.choose()
        // .bind(new FunctionBinding(FALSE))

        // false.choose false true
        // ->
        // FALSE.choose()
        // .bind(new FunctionBinding(FALSE))
        // .bind(new FunctionBinding(TRUE))

        assertTrue(f
                .bind(new FunctionBinding<Boolean, Function<Boolean, Boolean>>(
                        FALSE))
                .bind(new FunctionBinding<Boolean, Boolean>(TRUE)).evaluate()
                .getValue());
        assertFalse(t
                .bind(new FunctionBinding<Boolean, Function<Boolean, Boolean>>(
                        FALSE))
                .bind(new FunctionBinding<Boolean, Boolean>(TRUE)).evaluate()
                .getValue());

    }

    @Test
    public void test_Integer() {

        Evaluable<Integer> actual;

        Function<Integer, Function<Integer, Integer>> f = FALSE.choose();
        Function<Integer, Function<Integer, Integer>> t = TRUE.choose();

        actual = f.bind(
                new FunctionBinding<Integer, Function<Integer, Integer>>(
                        valueOf(1))).bind(
                new FunctionBinding<Integer, Integer>(valueOf(0)));
        assertThat(actual.evaluate().getValue(), is(0));

        actual = t.bind(
                new FunctionBinding<Integer, Function<Integer, Integer>>(
                        valueOf(1))).bind(
                new FunctionBinding<Integer, Integer>(valueOf(0)));
        assertThat(actual.evaluate().getValue(), is(1));

    }

}
