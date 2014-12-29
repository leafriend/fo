package net.folab.fo.ast;

public interface AstVisitor {

    public void visitClass(ClassGenerator cg);

    public void visitMethod(FunctionDeclaration fd);

    public byte[] toByteArray();

}
