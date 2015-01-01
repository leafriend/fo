package net.folab.fo.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;

import net.folab.fo.bytecode.JavaType;
import net.folab.fo.bytecode.StatementContext;
import net.folab.fo.jast.ConstructorInvocation;
import net.folab.fo.jast.LocalVariable;
import net.folab.fo.jast.Return;
import net.folab.fo.jast.Statement;

public class AstWriter implements AstVisitor, Opcodes {

    private final ClassWriter cv;

    private String className;

    public AstWriter(String className) {
        this.cv = new ClassWriter(false);
        this.className = className;
    }

    @Override
    public void visitClass(ClassGenerator cg) {

        String[] interfaces = new String[cg.interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = cg.interfaces[i].getName();
        }

        cv.visit(cg.java.version, // version
                cg.accessModifier.modifier, // access
                cg.name, // name
                null, // signature
                cg.superClass.getName(), // superName
                interfaces // interfaces
        );

        // TODO detect constructor of super class
        new FunctionDeclaration("<init>") //
                .setParameterTypes() //
                .setReturnType(JavaType.VOID) //
                .addStatement( //
                        new ConstructorInvocation( //
                                "java/lang/Object", //
                                "()V", //
                                new LocalVariable("this") //
                        ) //
                ) //
                .addStatement(Return.VOID) //
                .accept(this);

        for (FunctionDeclaration mg : cg.fds) {
            mg.accept(this);
        }

        cv.visitEnd();

    }

    @Override
    public void visitMethod(FunctionDeclaration fd) {

        String desc = "(";
        for (JavaType pt : fd.parameterTypes) {
            desc += pt.getDescName();
        }
        desc += ")";
        desc += fd.returnType.getDescName();

        int modifier = fd.access.modifier;
        if (fd.block.isStatic)
            modifier += ACC_STATIC;
        MethodVisitor mv = cv.visitMethod(//
                modifier, // access
                fd.name, // name
                desc, // desc
                null, // signature
                null // exceptions
                );

        mv.visitCode();

        StatementContext ctx = new StatementContext();

        if (!fd.block.isStatic) {
            ctx.addLocal("this", new JavaType(className));
        }

        for (Statement statement : fd.block.statements) {
            statement.generate(mv, ctx);
        }

        mv.visitMaxs(ctx.maxStack(), ctx.maxLocals());
        mv.visitEnd();

    }

    @Override
    public byte[] toByteArray() {
        return cv.toByteArray();
    }

}
