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

        int maxStack = 0;
        int maxLocals = 0;

        if ((modifier & ACC_STATIC) != ACC_STATIC)
            maxLocals++; // local variable for `this`

        if (returnType.equals(JavaType.VOID)) {
            mv.visitInsn(RETURN);
        } else if (returnType.equals(JavaType.BOOLEAN)
                || returnType.equals(JavaType.BYTE)
                || returnType.equals(JavaType.CHAR)
                || returnType.equals(JavaType.SHORT)
                || returnType.equals(JavaType.INT)) {
            maxStack++;
            mv.visitInsn(ICONST_0);
            mv.visitInsn(IRETURN);
        } else if (returnType.equals(JavaType.LONG)) {
            maxStack++;
            maxStack++;
            mv.visitInsn(LCONST_0);
            mv.visitInsn(LRETURN);
        } else if (returnType.equals(JavaType.FLOAT)) {
            maxStack++;
            mv.visitInsn(FCONST_0);
            mv.visitInsn(FRETURN);
        } else if (returnType.equals(JavaType.DOUBLE)) {
            maxStack++;
            maxStack++;
            mv.visitInsn(DCONST_0);
            mv.visitInsn(DRETURN);
        } else {
            maxStack++;
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
        }

        mv.visitMaxs(maxStack, maxLocals);
        mv.visitEnd();

    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(AccessModifier accessModifier) {
        this.accessModifier = accessModifier;
    }

    public JavaType getReturnType() {
        return returnType;
    }

    public void setReturnType(JavaType returnType) {
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaType[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(JavaType[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

}
