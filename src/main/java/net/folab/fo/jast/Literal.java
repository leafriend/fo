package net.folab.fo.jast;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

public class Literal implements Expression {

    private final Object value;

    private JavaType type;

    private Literal(Object value, JavaType type) {
        this.value = value;
        this.type = type;
    }

    public static Literal of(boolean value) {
        return new Literal(Boolean.valueOf(value), JavaType.BOOLEAN);
    }

    public static Literal of(byte value) {
        return new Literal(Byte.valueOf(value), JavaType.BYTE);
    }

    public static Literal of(char value) {
        return new Literal(Character.valueOf(value), JavaType.CHAR);
    }

    public static Literal of(short value) {
        return new Literal(Short.valueOf(value), JavaType.SHORT);
    }

    public static Literal of(int value) {
        return new Literal(Integer.valueOf(value), JavaType.INT);
    }

    public static Literal of(long value) {
        return new Literal(Long.valueOf(value), JavaType.LONG);
    }

    public static Literal of(float value) {
        return new Literal(Float.valueOf(value), JavaType.FLOAT);
    }

    public static Literal of(double value) {
        return new Literal(Double.valueOf(value), JavaType.DOUBLE);
    }

    public static Literal of(String value) {
        return new Literal(value, JavaType.STRING);
    }

    public static Literal nullValue() {
        return new Literal(null, JavaType.NULL) {
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
