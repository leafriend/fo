package net.folab.fo.runtime;

public class FunctionBinding<P extends Evaluable<P>, M extends Evaluable<M>>
        implements Binding<Function<P, M>, M> {

    private final Evaluable<P> parameter;

    public FunctionBinding(Evaluable<P> parameter) {
        this.parameter = parameter;
    }

    public Evaluable<M> bind(Function<P, M> value) {
        return value.apply(parameter);
    }

}
