package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.ast.AstVisitor;

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
