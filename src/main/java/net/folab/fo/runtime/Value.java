package net.folab.fo.runtime;

public class Value<V extends Value<?>> implements Evaluable<V> {

    @SuppressWarnings("unchecked")
    public V evaluate() {
        return (V) this;
    }

}
