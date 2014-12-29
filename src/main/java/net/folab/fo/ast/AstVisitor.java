package net.folab.fo.ast;

import net.folab.fo.bytecode.MethodGenerator;

public interface AstVisitor {

    public void visitMethod(MethodGenerator mg);

}
