package net.folab.fo.jast;

import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ConstructorInvocation implements Statement {

    private final String className;

    private final String desc;

    private final Expression[] args;

    public ConstructorInvocation(String className, String desc,
            Expression... args) {
        this.className = className;
        this.desc = desc;
        this.args = args;
    }

    @Override
    public void generate(MethodVisitor mv, StatementContext ctx) {
        for (Expression arg : args) {
            arg.generate(mv, ctx);
        }
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, className, "<init>", desc);
    }

}
