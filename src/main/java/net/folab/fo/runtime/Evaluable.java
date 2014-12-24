package net.folab.fo.runtime;

public abstract class Evaluable<T extends Evaluable<T>> {

    public abstract T evaluate();

}
