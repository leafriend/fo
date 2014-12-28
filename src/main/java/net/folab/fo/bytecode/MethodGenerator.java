package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.Statement;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator implements Opcodes {

    private final ClassGenerator cg;

    private Access access = Access.PUBLIC;

    private JavaType returnType = JavaType.VOID;

    private String name;

    private JavaType[] parameterTypes = new JavaType[0];

    private List<Statement> statements = new ArrayList<Statement>();

    protected MethodGenerator(ClassGenerator cg) {
        this.cg = cg;
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

    public Access getAccessModifier() {
        return access;
    }

    public MethodGenerator setAccessModifier(Access accessModifier) {
        this.access = accessModifier;
        return this;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public MethodGenerator setReturnType(JavaType returnType) {
        this.returnType = returnType;
        return this;
    }

    public String getName() {
        return name;
    }

    public MethodGenerator setName(String name) {
        this.name = name;
        return this;
    }

    public JavaType[] getParameterTypes() {
        return parameterTypes;
    }

    public MethodGenerator setParameterTypes(JavaType... parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public MethodGenerator addStatement(Statement statement) {
        statements.add(statement);
        return this;
    }

}
