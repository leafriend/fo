package net.folab.fo.ast;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.MethodGenerator;
import net.folab.fo.bytecode.StatementContext;
import net.folab.fo.jast.Statement;

public class AstWriter implements AstVisitor, Opcodes {

    private final ClassVisitor cv;

    private String className;

    public AstWriter(ClassVisitor cv, String className) {
        this.cv = cv;
        this.className = className;
    }

    public void visitMethod(MethodGenerator mg) {

        String desc = "(";
        for (JavaType pt : mg.parameterTypes) {
            desc += pt.getDescName();
        }
        desc += ")";
        desc += mg.returnType.getDescName();

        int modifier = mg.access.modifier;
        if (mg.isStatic)
            modifier += ACC_STATIC;
        MethodVisitor mv = cv.visitMethod(//
                modifier, // access
                mg.name, // name
                desc, // desc
                null, // signature
                null // exceptions
                );

        mv.visitCode();

        StatementContext ctx = new StatementContext();

        if (!mg.isStatic) {
            ctx.addLocal("this", new JavaType(className));
        }

        for (Statement statement : mg.statements) {
            statement.generate(mv, ctx);
        }

        mv.visitMaxs(ctx.maxStack(), ctx.maxLocals());
        mv.visitEnd();

    }

}
