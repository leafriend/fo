package net.folab.fo.ast;


public interface AstVisitor {

    public void visitClass(ClassGenerator cg);

    public void visitMethod(MethodGenerator mg);

    public byte[] toByteArray();

}
