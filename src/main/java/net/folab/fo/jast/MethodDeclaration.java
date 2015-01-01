package net.folab.fo.jast;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.bytecode.Access;
import net.folab.fo.bytecode.JavaType;
import net.folab.fo.jast.Block;
import net.folab.fo.jast.Statement;

public class MethodDeclaration extends MemberDeclaration {

    public final JavaType returnType;

    public final JavaType[] parameterTypes;

    public final Block block;

    public MethodDeclaration(ClassDeclaration cd, String name) {
        this(cd, Access.PUBLIC, JavaType.VOID, name, JavaType.VOID,
                new JavaType[0], false, new ArrayList<Statement>());
    }

    public MethodDeclaration(ClassDeclaration cd, Access access, JavaType type, String name,
            JavaType returnType, JavaType[] parameterTypes, boolean isStatic,
            List<Statement> statements) {
        super(cd, access, type, name);
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.block = new Block(isStatic, statements);
    }

    @Override
    public void accept(AstVisitor av) {
        av.visitMethod(this);
    }

    public MethodDeclaration setAccess(Access access) {
        return new MethodDeclaration(cd, access, type, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public MethodDeclaration setReturnType(JavaType returnType) {
        return new MethodDeclaration(cd, access, returnType, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public MethodDeclaration setParameterTypes(JavaType... parameterTypes) {
        return new MethodDeclaration(cd, access, type, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public MethodDeclaration addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(block.statements);
        statements.add(statement);
        return new MethodDeclaration(cd, access, type, name, returnType,
                parameterTypes, block.isStatic, statements);
    }

}