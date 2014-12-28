package net.folab.fo.ast;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LocalVariable implements Expression {

    private final String name;

    public LocalVariable(String name) {
        this.name = name;
    }

    public void generate(MethodVisitor mv, StatementContext ctx) {
        int index = ctx.indexOfLocal(name);
        ctx.incStack();
        mv.visitVarInsn(Opcodes.ALOAD, index);
    }

    public JavaType getType() {
        throw new RuntimeException();
    }

}
