package net.folab.fo.ast;

import net.folab.fo.bytecode.ClassGenerator;
import net.folab.fo.bytecode.MethodGenerator;

public interface AstVisitor {

    public void visitClass(ClassGenerator cg);

    public void visitMethod(MethodGenerator mg);

}
