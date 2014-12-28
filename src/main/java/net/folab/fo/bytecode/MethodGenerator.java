package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.folab.fo.ast.Statement;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator implements Opcodes {

    private final ClassGenerator cg;

    private final Access access;

    private final JavaType returnType;

    private final String name;

    private final JavaType[] parameterTypes;

    private final List<Statement> statements;

    public MethodGenerator(ClassGenerator cg, Access access,
            JavaType returnType, String name, JavaType[] parameterTypes,
            List<Statement> statements) {
        this.cg = cg;
        this.access = access;
        this.returnType = returnType;
        this.name = name;
        this.parameterTypes = parameterTypes;
        this.statements = Collections.unmodifiableList(statements);
    }

    public static MethodGenerator build(String name) {
        return new MethodGenerator(null, Access.PUBLIC, JavaType.VOID, name,
                new JavaType[0], new ArrayList<Statement>());
    }

    public void generate(ClassWriter cw) {
        String desc = "(";
        for (JavaType pt : parameterTypes) {
            desc += pt.getDescName();
        }
        desc += ")";
        desc += returnType.getDescName();

        int modifier = access.modifier;
        MethodVisitor mv = cw.visitMethod(//
                modifier, // access
                name, // name
                desc, // desc
                null, // signature
                null // exceptions
                );

        mv.visitCode();

        StatementContext ctx = new StatementContext();

        if ((modifier & ACC_STATIC) != ACC_STATIC) {
            ctx.addLocal("this", new JavaType(cg.getName()));
        }

        for (Statement statement : statements) {
            statement.generate(mv, ctx);
        }

        mv.visitMaxs(ctx.maxStack(), ctx.maxLocals());
        mv.visitEnd();

    }

    public MethodGenerator setClassGenerator(ClassGenerator cg) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, statements);
    }

    public Access getAccessModifier() {
        return access;
    }

    public MethodGenerator setAccessModifier(Access accessModifier) {
        return new MethodGenerator(cg, accessModifier, returnType, name,
                parameterTypes, statements);
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public MethodGenerator setReturnType(JavaType returnType) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, statements);
    }

    public String getName() {
        return name;
    }

    public JavaType[] getParameterTypes() {
        return parameterTypes;
    }

    public MethodGenerator setParameterTypes(JavaType... parameterTypes) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, statements);
    }

    public MethodGenerator addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(this.statements);
        statements.add(statement);
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, statements);
    }

}
