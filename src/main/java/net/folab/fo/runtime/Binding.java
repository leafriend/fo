package net.folab.fo.runtime;

public interface Binding<V extends Evaluable<V>, P extends Evaluable<P>, R extends Evaluable<R>> {

    public Function<P, R> bind(V value);

}
