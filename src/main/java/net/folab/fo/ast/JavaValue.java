package net.folab.fo.ast;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

public class JavaValue implements Expression {

    private final Object value;

    private JavaType type;

    private JavaValue(Object value, JavaType type) {
        this.value = value;
        this.type = type;
    }

    public static JavaValue of(boolean value) {
        return new JavaValue(Boolean.valueOf(value), JavaType.BOOLEAN);
    }

    public static JavaValue of(byte value) {
        return new JavaValue(Byte.valueOf(value), JavaType.BYTE);
    }

    public static JavaValue of(char value) {
        return new JavaValue(Character.valueOf(value), JavaType.CHAR);
    }

    public static JavaValue of(short value) {
        return new JavaValue(Short.valueOf(value), JavaType.SHORT);
    }

    public static JavaValue of(int value) {
        return new JavaValue(Integer.valueOf(value), JavaType.INT);
    }

    public static JavaValue of(long value) {
        return new JavaValue(Long.valueOf(value), JavaType.LONG);
    }

    public static JavaValue of(float value) {
        return new JavaValue(Float.valueOf(value), JavaType.FLOAT);
    }

    public static JavaValue of(double value) {
        return new JavaValue(Double.valueOf(value), JavaType.DOUBLE);
    }

    public static JavaValue nullValue() {
        return new JavaValue(null, JavaType.NULL) {
            @Override
            public void generate(MethodVisitor mv, StatementContext context) {
                context.incStack();
                mv.visitInsn(Opcodes.ACONST_NULL);
            }
        };
    }

    public void generate(MethodVisitor mv, StatementContext context) {
        switch (type.getSize()) {
        case 0:

            break;
        case 1:
            context.incStack();
            mv.visitLdcInsn(value);
            break;
        case 2:
            context.incStack().incStack();
            mv.visitLdcInsn(value);
            break;

        default:
            break;
        }
    }

    public JavaType getType() {
        return type;
    }

}
