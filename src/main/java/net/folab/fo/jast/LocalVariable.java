package net.folab.fo.jast;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;

public class LocalVariable implements Expression {

    private final String name;

    public LocalVariable(String name) {
        this.name = name;
    }

    public void generate(MethodVisitor mv, StatementContext ctx) {
        int index = ctx.indexOfLocal(name);
        JavaType type = ctx.typeOfLocal(name);
        ctx.incStack();
        mv.visitVarInsn(type.loadOpcode, index);
    }

    public JavaType getType() {
        throw new RuntimeException();
    }

}
