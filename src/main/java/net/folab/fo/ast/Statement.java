package net.folab.fo.ast;

import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;

public interface Statement {

    public void generate(MethodVisitor mv, StatementContext ctx);

}
