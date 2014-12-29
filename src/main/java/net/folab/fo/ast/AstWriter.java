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
        MethodGenerator.build("<init>") //
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
                .setClassGenerator(cg) //
                .accept(this);

        for (MethodGenerator mg : cg.methodGenerators) {
            mg.accept(this);
        }

        cv.visitEnd();

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

    public byte[] toByteArray() {
        return cv.toByteArray();
    }

}
