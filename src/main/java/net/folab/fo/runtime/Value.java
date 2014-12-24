package net.folab.fo.runtime;

public class Value<V extends Value<V>> implements Evaluable<V> {

    @SuppressWarnings("unchecked")
    public V evaluate() {
        return (V) this;
    }

}
