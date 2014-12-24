package net.folab.fo.runtime;

public interface Binding<V extends Evaluable<V>, M extends Evaluable<M>> {

    public Evaluable<M> bind(V value);

}
