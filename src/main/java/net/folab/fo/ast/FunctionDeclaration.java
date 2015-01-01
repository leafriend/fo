package net.folab.fo.ast;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.bytecode.Access;
import net.folab.fo.bytecode.JavaType;
import net.folab.fo.jast.Block;
import net.folab.fo.jast.Statement;

public class FunctionDeclaration extends MemberDeclaration {

    public final JavaType returnType;

    public final JavaType[] parameterTypes;

    public final Block block;

    public FunctionDeclaration(String name) {
        this(Access.PUBLIC, JavaType.VOID, name, JavaType.VOID,
                new JavaType[0], false, new ArrayList<Statement>());
    }

    public FunctionDeclaration(Access access, JavaType type, String name,
            JavaType returnType, JavaType[] parameterTypes, boolean isStatic,
            List<Statement> statements) {
        super(access, type, name);
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.block = new Block(isStatic, statements);
    }

    public void accept(AstVisitor cv) {
        cv.visitMethod(this);
    }

    public FunctionDeclaration setAccess(Access access) {
        return new FunctionDeclaration(access, type, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public FunctionDeclaration setReturnType(JavaType returnType) {
        return new FunctionDeclaration(access, returnType, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public FunctionDeclaration setParameterTypes(JavaType... parameterTypes) {
        return new FunctionDeclaration(access, type, name, returnType,
                parameterTypes, block.isStatic, block.statements);
    }

    public FunctionDeclaration addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(block.statements);
        statements.add(statement);
        return new FunctionDeclaration(access, type, name, returnType,
                parameterTypes, block.isStatic, statements);
    }

}
