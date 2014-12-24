package net.folab.fo.runtime._fo._lang;

import static net.folab.fo.runtime._fo._lang.Boolean.*;
import static net.folab.fo.runtime._fo._lang.Integer.*;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;

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
    public void test() {

        Function<Evaluable<Boolean>, Function<Evaluable<Boolean>, Evaluable<Boolean>>> falseChoose = FALSE
                .choose();
        Function<Evaluable<Boolean>, Function<Evaluable<Boolean>, Evaluable<Boolean>>> trueChoose = TRUE
                .choose();

        System.out.println(falseChoose.apply(FALSE).apply(TRUE).evaluate()
                .getValue());
        System.out.println(trueChoose.apply(FALSE).apply(TRUE).evaluate()
                .getValue());

    }

    @Test
    public void testI() {

        Function<Evaluable<Integer>, Function<Evaluable<Integer>, Evaluable<Integer>>> falseChoose = FALSE
                .choose();
        Function<Evaluable<Integer>, Function<Evaluable<Integer>, Evaluable<Integer>>> trueChoose = TRUE
                .choose();

        System.out.println(falseChoose.apply(valueOf(1)).apply(valueOf(0))
                .evaluate().getValue());
        System.out.println(trueChoose.apply(valueOf(1)).apply(valueOf(0))
                .evaluate().getValue());

    }

}
