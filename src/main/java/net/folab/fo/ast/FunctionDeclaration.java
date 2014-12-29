package net.folab.fo.ast;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.bytecode.Access;
import net.folab.fo.bytecode.JavaType;
import net.folab.fo.jast.Block;
import net.folab.fo.jast.Statement;

public class FunctionDeclaration {

    public final Access access;

    public final JavaType returnType;

    public final String name;

    public final JavaType[] parameterTypes;

    public final Block block;

    public FunctionDeclaration(Access access, JavaType returnType,
            String name, JavaType[] parameterTypes, boolean isStatic,
            List<Statement> statements) {
        this.access = access;
        this.returnType = returnType;
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.block = new Block(isStatic, statements);
    }

    public static FunctionDeclaration build(String name) {
        return new FunctionDeclaration(Access.PUBLIC, JavaType.VOID, name,
                new JavaType[0], false, new ArrayList<Statement>());
    }

    public void accept(AstVisitor cv) {
        cv.visitMethod(this);
    }

    public FunctionDeclaration setClassGenerator(ClassGenerator cg) {
        return new FunctionDeclaration(access, returnType, name, parameterTypes,
                block.isStatic, block.statements);
    }

    public FunctionDeclaration setAccess(Access access) {
        return new FunctionDeclaration(access, returnType, name, parameterTypes,
                block.isStatic, block.statements);
    }

    public FunctionDeclaration setReturnType(JavaType returnType) {
        return new FunctionDeclaration(access, returnType, name, parameterTypes,
                block.isStatic, block.statements);
    }

    public FunctionDeclaration setParameterTypes(JavaType... parameterTypes) {
        return new FunctionDeclaration(access, returnType, name, parameterTypes,
                block.isStatic, block.statements);
    }

    public FunctionDeclaration addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(block.statements);
        statements.add(statement);
        return new FunctionDeclaration(access, returnType, name, parameterTypes,
                block.isStatic, statements);
    }

}
