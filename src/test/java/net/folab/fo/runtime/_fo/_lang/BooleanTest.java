package net.folab.fo.runtime._fo._lang;

import static net.folab.fo.runtime._fo._lang.Boolean.*;
import static net.folab.fo.runtime._fo._lang.Integer.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.folab.fo.runtime._fo._lang._Boolean.choose;

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

        choose<Boolean> f = FALSE.choose();
        choose<Boolean> t = TRUE.choose();

        assertTrue(f.apply(FALSE).apply(TRUE).evaluate().getValue());
        assertFalse(t.apply(FALSE).apply(TRUE).evaluate().getValue());

    }

    @Test
    public void test_Integer() {

        choose<Integer> f = FALSE.choose();
        choose<Integer> t = TRUE.choose();

        Integer one = valueOf(1);
        Integer zero = valueOf(0);

        assertThat(f.apply(one).apply(zero).evaluate().getValue(), is(0));
        assertThat(t.apply(one).apply(zero).evaluate().getValue(), is(1));

    }

}
