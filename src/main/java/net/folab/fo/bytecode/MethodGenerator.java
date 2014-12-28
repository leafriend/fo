package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.jast.Block;
import net.folab.fo.jast.Statement;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator extends Block implements Opcodes {

    private final ClassGenerator cg;

    private final Access access;

    private final JavaType returnType;

    private final String name;

    private final JavaType[] parameterTypes;

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

    public void generate(ClassVisitor cv) {
        String desc = "(";
        for (JavaType pt : parameterTypes) {
            desc += pt.getDescName();
        }
        desc += ")";
        desc += returnType.getDescName();

        int modifier = access.modifier;
        if (isStatic)
            modifier += ACC_STATIC;
        MethodVisitor mv = cv.visitMethod(//
                modifier, // access
                name, // name
                desc, // desc
                null, // signature
                null // exceptions
                );

        mv.visitCode();

        StatementContext ctx = new StatementContext();

        if (!isStatic) {
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
                parameterTypes, isStatic, statements);
    }

    public Access getAccessModifier() {
        return access;
    }

    public MethodGenerator setAccessModifier(Access accessModifier) {
        return new MethodGenerator(cg, accessModifier, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public MethodGenerator setReturnType(JavaType returnType) {
        return new MethodGenerator(cg, access, returnType, name,
                parameterTypes, isStatic, statements);
    }

    public String getName() {
        return name;
    }

    public JavaType[] getParameterTypes() {
        return parameterTypes;
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
