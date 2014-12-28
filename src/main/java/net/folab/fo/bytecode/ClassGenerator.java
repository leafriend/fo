package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.ConstructorInvocation;
import net.folab.fo.ast.LocalVariable;
import net.folab.fo.ast.Return;

import org.objectweb.asm.ClassWriter;

public class ClassGenerator {

    private Java java = Java.V1_5;

    private Access accessModifier = Access.PUBLIC;

    private String name;

    private JavaType superClass = JavaType.OBJECT;

    private JavaType[] interfaces = new JavaType[0];

    private List<MethodGenerator> methodGenerators = new ArrayList<MethodGenerator>();

    public byte[] generateBytecode() {

        ClassWriter cw = new ClassWriter(false);

        String[] interfaces = new String[this.interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = this.interfaces[i].getName();
        }

        cw.visit(java.version, // version
                accessModifier.modifier, // access
                name, // name
                null, // signature
                superClass.getName(), // superName
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
                .setClassGenerator(this) //
                .generate(cw);

        for (MethodGenerator mg : methodGenerators) {
            mg.generate(cw);
        }

        cw.visitEnd();

        return cw.toByteArray();

    }

    public Java getJavaVersion() {
        return java;
    }

    public ClassGenerator setJavaVersion(Java javaVersion) {
        this.java = javaVersion;
        return this;
    }

    public Access getAccessModifier() {
        return accessModifier;
    }

    public ClassGenerator setAccessModifier(Access accessModifier) {
        this.accessModifier = accessModifier;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClassGenerator setName(String name) {
        this.name = name.replaceAll("\\.", "/");
        return this;
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public ClassGenerator setSuperClass(JavaType superClass) {
        this.superClass = superClass;
        return this;
    }

    public JavaType[] getInterfaces() {
        return interfaces;
    }

    public ClassGenerator setInterfaces(JavaType... interfaces) {
        this.interfaces = interfaces;
        return this;
    }

    public ClassGenerator addMethod(MethodGenerator mg) {
        methodGenerators.add(mg.setClassGenerator(this));
        return this;
    }

}
