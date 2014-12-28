package net.folab.fo.ast;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Return implements Statement {

    public static final Statement VOID = new Return(Expression.VOID);

    private final Expression returnValue;

    public Return(Expression returnValue) {
        this.returnValue = returnValue;
    }

    public void generate(MethodVisitor mv, StatementContext ctx) {

        returnValue.generate(mv, ctx);

        JavaType returnType = returnValue.getType();
        if (returnType.equals(JavaType.VOID)) {
            mv.visitInsn(Opcodes.RETURN);
            ctx.clearStack();

        } else if (returnType.equals(JavaType.BOOLEAN)
                || returnType.equals(JavaType.BYTE)
                || returnType.equals(JavaType.CHAR)
                || returnType.equals(JavaType.SHORT)
                || returnType.equals(JavaType.INT)) {
            mv.visitInsn(Opcodes.IRETURN);
            ctx.clearStack();

        } else if (returnType.equals(JavaType.LONG)) {
            mv.visitInsn(Opcodes.LRETURN);
            ctx.clearStack();

        } else if (returnType.equals(JavaType.FLOAT)) {
            mv.visitInsn(Opcodes.FRETURN);
            ctx.clearStack();

        } else if (returnType.equals(JavaType.DOUBLE)) {
            mv.visitInsn(Opcodes.DRETURN);
            ctx.clearStack();

        } else {
            mv.visitInsn(Opcodes.ARETURN);
            ctx.clearStack();

        }

    }

}
