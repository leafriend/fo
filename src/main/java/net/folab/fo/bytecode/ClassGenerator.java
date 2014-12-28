package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.ConstructorInvocation;
import net.folab.fo.ast.LocalVariable;
import net.folab.fo.ast.Return;

import org.objectweb.asm.ClassWriter;

public class ClassGenerator {

    private JavaVersion javaVersion = JavaVersion.V1_5;

    private AccessModifier accessModifier = AccessModifier.PUBLIC;

    private String name;

    private JavaType superClass = new JavaType("java/lang/Object");

    private JavaType[] interfaces = new JavaType[0];

    private List<MethodGenerator> methodGenerators = new ArrayList<MethodGenerator>();

    public byte[] generateBytecode() {

        ClassWriter cw = new ClassWriter(false);

        String[] interfaces = new String[this.interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = this.interfaces[i].getName();
        }

        cw.visit(javaVersion.version, // version
                accessModifier.modifier, // access
                name, // name
                null, // signature
                superClass.getName(), // superName
                interfaces // interfaces
        );

        // TODO detect constructor of super class
        new MethodGenerator(this) //
                .setName("<init>") //
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
                .generate(cw);

        for (MethodGenerator mg : methodGenerators) {
            mg.generate(cw);
        }

        cw.visitEnd();

        return cw.toByteArray();

    }

    public JavaVersion getJavaVersion() {
        return javaVersion;
    }

    public ClassGenerator setJavaVersion(JavaVersion javaVersion) {
        this.javaVersion = javaVersion;
        return this;
    }

    public AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public ClassGenerator setAccessModifier(AccessModifier accessModifier) {
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

    public MethodGenerator addMethod() {
        MethodGenerator mg = new MethodGenerator(this);
        methodGenerators.add(mg);
        return mg;
    }

}
