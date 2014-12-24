package net.folab.fo.runtime;

public interface Evaluable<Q extends Evaluable<?>> {

    public Q evaluate();

}
