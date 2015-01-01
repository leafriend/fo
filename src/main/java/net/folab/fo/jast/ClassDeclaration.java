package net.folab.fo.jast;

import java.util.ArrayList;
import java.util.List;

import net.folab.fo.bytecode.Access;
import net.folab.fo.bytecode.Java;
import net.folab.fo.bytecode.JavaType;

public class ClassDeclaration {

    public final Java java;

    public final Access accessModifier;

    public final String name;

    public final JavaType superClass;

    public final JavaType[] interfaces;

    public final List<MethodDeclaration> fds;

    public ClassDeclaration(String name) {
        this(Java.V1_5, Access.PUBLIC, name.replaceAll("\\.", "/"),
                JavaType.OBJECT, new JavaType[0],
                new ArrayList<MethodDeclaration>());
    }

    public ClassDeclaration(Java java, Access accessModifier, String name,
            JavaType superClass, JavaType[] interfaces,
            List<MethodDeclaration> fds) {
        this.java = java;
        this.accessModifier = accessModifier;
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.fds = fds;
    }

    public void accept(AstVisitor av) {
        av.visitClass(this);
    }

    public Java getJavaVersion() {
        return java;
    }

    public ClassDeclaration setJavaVersion(Java javaVersion) {
        return new ClassDeclaration(javaVersion, accessModifier, name,
                superClass, interfaces, fds);
    }

    public Access getAccessModifier() {
        return accessModifier;
    }

    public ClassDeclaration setAccessModifier(Access accessModifier) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public String getName() {
        return name;
    }

    public ClassDeclaration setName(String name) {
        return new ClassDeclaration(java, accessModifier, name.replaceAll("\\.",
                "/"), superClass, interfaces, fds);
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public ClassDeclaration setSuperClass(JavaType superClass) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public JavaType[] getInterfaces() {
        return interfaces;
    }

    public ClassDeclaration setInterfaces(JavaType... interfaces) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, fds);
    }

    public ClassDeclaration addMethod(MethodDeclaration fd) {
        List<MethodDeclaration> methodGenerators = new ArrayList<MethodDeclaration>(
                this.fds);
        methodGenerators.add(fd);
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

}
