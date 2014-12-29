package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.AstVisitor;
import net.folab.fo.ast.AstWriter;
import net.folab.fo.jast.ConstructorInvocation;
import net.folab.fo.jast.LocalVariable;
import net.folab.fo.jast.Return;

import org.objectweb.asm.ClassWriter;

public class ClassGenerator {

    public final Java java;

    public final Access accessModifier;

    public final String name;

    public final JavaType superClass;

    public final JavaType[] interfaces;

    public final List<MethodGenerator> methodGenerators;

    public ClassGenerator(String name) {
        this(Java.V1_5, Access.PUBLIC, name.replaceAll("\\.", "/"),
                JavaType.OBJECT, new JavaType[0],
                new ArrayList<MethodGenerator>());
    }

    public ClassGenerator(Java java, Access accessModifier, String name,
            JavaType superClass, JavaType[] interfaces,
            List<MethodGenerator> methodGenerators) {
        this.java = java;
        this.accessModifier = accessModifier;
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.methodGenerators = methodGenerators;
    }

    public void accept(AstVisitor av) {
        av.visitClass(this);
    }

    public byte[] generateBytecode() {

        ClassWriter cw = new ClassWriter(false);
        AstVisitor av = new AstWriter(cw, name);

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
                .accept(av);

        for (MethodGenerator mg : methodGenerators) {
            mg.accept(av);
        }

        cw.visitEnd();

        return cw.toByteArray();

    }

    public Java getJavaVersion() {
        return java;
    }

    public ClassGenerator setJavaVersion(Java javaVersion) {
        return new ClassGenerator(javaVersion, accessModifier, name,
                superClass, interfaces, methodGenerators);
    }

    public Access getAccessModifier() {
        return accessModifier;
    }

    public ClassGenerator setAccessModifier(Access accessModifier) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

    public String getName() {
        return name;
    }

    public ClassGenerator setName(String name) {
        return new ClassGenerator(java, accessModifier, name.replaceAll("\\.",
                "/"), superClass, interfaces, methodGenerators);
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public ClassGenerator setSuperClass(JavaType superClass) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

    public JavaType[] getInterfaces() {
        return interfaces;
    }

    public ClassGenerator setInterfaces(JavaType... interfaces) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

    public ClassGenerator addMethod(MethodGenerator mg) {
        List<MethodGenerator> methodGenerators = new ArrayList<MethodGenerator>(
                this.methodGenerators);
        methodGenerators.add(mg);
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

}
