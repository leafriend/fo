package net.folab.fo.runtime;

public abstract class Function<P extends Evaluable<P>, R extends Evaluable<R>>
        extends Evaluable<Function<P, R>> {

    public abstract Evaluable<R> apply(Evaluable<P> parameter);

    @Override
    public Function<P, R> evaluate() {
        return this;
    }

}
