package net.folab.fo.ast;

import net.folab.fo.bytecode.MethodGenerator;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class AstWriter implements AstVisitor, Opcodes {

    private final ClassVisitor cv;

    private String className;

    public AstWriter(ClassVisitor cv, String className) {
        this.cv = cv;
        this.className = className;
    }

    public void visitMethod(MethodGenerator mg) {

    }

}
