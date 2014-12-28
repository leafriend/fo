package net.folab.fo.jast;

import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;

public class Return implements Statement {

    public static final Statement VOID = new Return(Expression.VOID);

    private final Expression returnValue;

    public Return(Expression returnValue) {
        this.returnValue = returnValue;
    }

    public void generate(MethodVisitor mv, StatementContext ctx) {

        returnValue.generate(mv, ctx);

        mv.visitInsn(returnValue.getType().returnOpcode);
        ctx.clearStack();

    }

}
