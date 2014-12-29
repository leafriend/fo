package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.AstVisitor;
import net.folab.fo.jast.Block;
import net.folab.fo.jast.Statement;

public class MethodGenerator extends Block {

    public final ClassGenerator cg;

    public final Access access;

    public final JavaType returnType;

    public final String name;

    public final JavaType[] parameterTypes;

    public MethodGenerator(ClassGenerator cg, Access access,
            JavaType returnType, String name, JavaType[] parameterTypes,
            boolean isStatic, List<Statement> statements) {
        super(isStatic, statements);
        this.cg = cg;
        this.access = access;
        this.returnType = returnType;
        this.name = name;
        this.parameterTypes = parameterTypes;
    }

    public static MethodGenerator build(String name) {
        return new MethodGenerator(null, Access.PUBLIC, JavaType.VOID, name,
                new JavaType[0], false, new ArrayList<Statement>());
    }

    public void accept(AstVisitor cv) {
        cv.visitMethod(this);
    }

    public MethodGenerator setClassGenerator(ClassGenerator cg) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public MethodGenerator setAccess(Access access) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public MethodGenerator setReturnType(JavaType returnType) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public MethodGenerator setParameterTypes(JavaType... parameterTypes) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public MethodGenerator addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(this.statements);
        statements.add(statement);
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

}
