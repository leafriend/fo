package net.folab.fo.runtime;

public abstract class Function<P extends Evaluable<P>, R extends Evaluable<R>>
        implements Evaluable<Function<P, R>> {

    public abstract Evaluable<R> apply(Evaluable<P> parameter);

    public Function<P, R> evaluate() {
        return this;
    }

}
