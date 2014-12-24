package net.folab.fo.runtime;

public abstract class Function<P extends Evaluable<?>, R extends Evaluable<?>>
        implements Evaluable<Function<P, R>> {

    public abstract R apply(P parameter);

    public Function<P, R> evaluate() {
        return this;
    }

}
