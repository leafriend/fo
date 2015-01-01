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

    public final List<MemberDeclaration> mds;

    public ClassDeclaration(String name) {
        this(Java.V1_5, Access.PUBLIC, name.replaceAll("\\.", "/"),
                JavaType.OBJECT, new JavaType[0],
                new ArrayList<MemberDeclaration>());
    }

    public ClassDeclaration(Java java, Access accessModifier, String name,
            JavaType superClass, JavaType[] interfaces,
            List<MemberDeclaration> mds) {
        this.java = java;
        this.accessModifier = accessModifier;
        this.name = name;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.mds = mds;
    }

    public void accept(AstVisitor av) {
        av.visitClass(this);
    }

    public Java getJavaVersion() {
        return java;
    }

    public ClassDeclaration setJavaVersion(Java javaVersion) {
        return new ClassDeclaration(javaVersion, accessModifier, name,
                superClass, interfaces, mds);
    }

    public Access getAccessModifier() {
        return accessModifier;
    }

    public ClassDeclaration setAccessModifier(Access accessModifier) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, mds);
    }

    public String getName() {
        return name;
    }

    public ClassDeclaration setName(String name) {
        return new ClassDeclaration(java, accessModifier, name.replaceAll("\\.",
                "/"), superClass, interfaces, mds);
    }

    public JavaType getSuperClass() {
        return superClass;
    }

    public ClassDeclaration setSuperClass(JavaType superClass) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, mds);
    }

    public JavaType[] getInterfaces() {
        return interfaces;
    }

    public ClassDeclaration setInterfaces(JavaType... interfaces) {
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, mds);
    }

    public ClassDeclaration addMember(MemberDeclaration mdd) {
        List<MemberDeclaration> methodGenerators = new ArrayList<MemberDeclaration>(
                this.mds);
        methodGenerators.add(mdd);
        return new ClassDeclaration(java, accessModifier, name, superClass,
                interfaces, methodGenerators);
    }

}
