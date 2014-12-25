package net.folab.fo.bytecode;

public class JavaType {

    private final String name;

    private final JavaType[] typeParameters;

    public JavaType(String name, JavaType... typeParameters) {
        this.name = name;
        this.typeParameters = typeParameters;
    }

    public String getName() {
        return name;
    }

    public JavaType[] getTypeParameters() {
        return typeParameters;
    }

}
