package net.folab.fo.ast;

import java.util.ArrayList;
import java.util.List;

import net.folab.metaj.bytecode.Access;
import net.folab.metaj.bytecode.Java;
import net.folab.metaj.bytecode.JavaType;

public class ClassGenerator {

    public final Java java;

    public final Access accessModifier;

    public final String name;

    public final JavaType superClass;

    public final JavaType[] interfaces;

    public final List<FunctionDeclaration> fds;

    public ClassGenerator(String name) {
        this(Java.V1_5, Access.PUBLIC, name.replaceAll("\\.", "/"),
                JavaType.OBJECT, new JavaType[0],
                new ArrayList<FunctionDeclaration>());
    }

    public ClassGenerator(Java java, Access accessModifier, String name,
            JavaType superClass, JavaType[] interfaces,
            List<FunctionDeclaration> fds) {
        this.java = java;
        this.accessModifier = accessModifier;
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.fds = fds;
    }

    public Java getJavaVersion() {
        return java;
    }

    public ClassGenerator setJavaVersion(Java javaVersion) {
        return new ClassGenerator(javaVersion, accessModifier, name,
                superClass, interfaces, fds);
    }

    public Access getAccessModifier() {
        return accessModifier;
    }

    public ClassGenerator setAccessModifier(Access accessModifier) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public String getName() {
        return name;
    }

    public ClassGenerator setName(String name) {
        return new ClassGenerator(java, accessModifier, name.replaceAll("\\.",
                "/"), superClass, interfaces, fds);
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public ClassGenerator setSuperClass(JavaType superClass) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public JavaType[] getInterfaces() {
        return interfaces;
    }

    public ClassGenerator setInterfaces(JavaType... interfaces) {
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public ClassGenerator addMethod(FunctionDeclaration fd) {
        List<FunctionDeclaration> methodGenerators = new ArrayList<FunctionDeclaration>(
                this.fds);
        methodGenerators.add(fd);
        return new ClassGenerator(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

}
