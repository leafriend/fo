package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.Statement;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator implements Opcodes {

    private final ClassGenerator cg;

    private AccessModifier accessModifier = AccessModifier.PUBLIC;

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

        int modifier = accessModifier.modifier;
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
            ctx.addLocal("this");
        }

        for (Statement statement : statements) {
            statement.generate(mv, ctx);
        }

//        if (returnType.equals(JavaType.VOID)) {
//            mv.visitInsn(RETURN);
//            ctx.clearStack();
//
//        } else if (returnType.equals(JavaType.BOOLEAN)
//                || returnType.equals(JavaType.BYTE)
//                || returnType.equals(JavaType.CHAR)
//                || returnType.equals(JavaType.SHORT)
//                || returnType.equals(JavaType.INT)) {
//            ctx.incStack();
//            mv.visitInsn(ICONST_0);
//            mv.visitInsn(IRETURN);
//            ctx.clearStack();
//
//        } else if (returnType.equals(JavaType.LONG)) {
//            ctx.incStack().incStack();
//            mv.visitInsn(LCONST_0);
//            mv.visitInsn(LRETURN);
//            ctx.clearStack();
//
//        } else if (returnType.equals(JavaType.FLOAT)) {
//            ctx.incStack();
//            mv.visitInsn(FCONST_0);
//            mv.visitInsn(FRETURN);
//            ctx.clearStack();
//
//        } else if (returnType.equals(JavaType.DOUBLE)) {
//            ctx.incStack().incStack();
//            mv.visitInsn(DCONST_0);
//            mv.visitInsn(DRETURN);
//            ctx.clearStack();
//
//        } else {
//            ctx.incStack();
//            mv.visitInsn(ACONST_NULL);
//            mv.visitInsn(ARETURN);
//            ctx.clearStack();
//
//        }

        mv.visitMaxs(ctx.maxStack(), ctx.maxLocals());
        mv.visitEnd();

    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public MethodGenerator setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
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
