package net.folab.fo.bytecode;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StatementContextTest {

    private StatementContext ctx;

    @Before
    public void setUp() throws Exception {
        ctx = new StatementContext();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStack() {
        assertThat(ctx.maxStack(), is(0));
        ctx.incStack();
        assertThat(ctx.maxStack(), is(1));
        ctx.incStack().incStack();
        assertThat(ctx.maxStack(), is(3));
        ctx.decStack();
        assertThat(ctx.maxStack(), is(3));
        ctx.clearStack();
        assertThat(ctx.maxStack(), is(3));
    }

    @Test
    public void testLocals() {
        assertThat(ctx.maxLocals(), is(0));
        ctx.addLocal("foo", JavaType.INT);
        assertThat(ctx.indexOfLocal("foo"), is(0));
        assertThat(ctx.maxLocals(), is(1));
    }

}
