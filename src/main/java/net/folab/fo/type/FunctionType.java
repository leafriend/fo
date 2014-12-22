package net.folab.fo.type;

public class FunctionType implements Type {

    private final Type returnType;

    private final Type[] parameterTypes;

    public FunctionType(Type returnType, Type[] parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Type[] getParameterTypes() {
        return parameterTypes;
    }

}
