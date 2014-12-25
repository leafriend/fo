package net.folab.fo.bytecode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MethodGenerator implements Opcodes {

    private final ClassGenerator cg;

    private AccessModifier accessModifier = AccessModifier.PUBLIC;

    private JavaType returnType = JavaType.VOID;

    private String name;

    private JavaType[] parameterTypes = new JavaType[0];

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

        Counter maxStack = new Counter();
        Counter maxLocals = new Counter();

        if ((modifier & ACC_STATIC) != ACC_STATIC)
            maxLocals.inc(); // local variable for `this`

        if (returnType.equals(JavaType.VOID)) {
            mv.visitInsn(RETURN);
            maxStack.clear();

        } else if (returnType.equals(JavaType.BOOLEAN)
                || returnType.equals(JavaType.BYTE)
                || returnType.equals(JavaType.CHAR)
                || returnType.equals(JavaType.SHORT)
                || returnType.equals(JavaType.INT)) {
            maxStack.inc();
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
            maxStack.clear();

        } else if (returnType.equals(JavaType.LONG)) {
            maxStack.inc().inc();
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LRETURN);
            maxStack.clear();

        } else if (returnType.equals(JavaType.FLOAT)) {
            maxStack.inc();
            mv.visitInsn(FCONST_0);
            mv.visitInsn(FRETURN);
            maxStack.clear();

        } else if (returnType.equals(JavaType.DOUBLE)) {
            maxStack.inc().inc();
            mv.visitInsn(DCONST_0);
            mv.visitInsn(DRETURN);
            maxStack.clear();

        } else {
            maxStack.inc();
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            maxStack.clear();

        }

        mv.visitMaxs(maxStack.max(), maxLocals.max());
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

    public MethodGenerator setParameterTypes(JavaType[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

}
