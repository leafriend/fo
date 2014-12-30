package net.folab.fo.jast;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;

import org.objectweb.asm.MethodVisitor;

public interface Expression {

    public static final Expression VOID = new Expression() {

        @Override
        public JavaType getType() {
            return JavaType.VOID;
        }

        @Override
        public void generate(MethodVisitor mv, StatementContext context) {

        }

    };

    public void generate(MethodVisitor mv, StatementContext context);

    public JavaType getType();

}
