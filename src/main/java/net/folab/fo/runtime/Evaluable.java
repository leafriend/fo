package net.folab.fo.runtime;

public interface Evaluable<T extends Evaluable<T>> {

    public T evaluate();

}
