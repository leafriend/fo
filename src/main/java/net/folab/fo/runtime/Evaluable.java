package net.folab.fo.runtime;

public abstract class Evaluable<T extends Evaluable<T>> {

    public abstract T evaluate();

    public <M extends Evaluable<M>> Evaluable<M> bind(
            final Binding<T, M> binding) {

        return new Evaluable<M>() {

            @Override
            public M evaluate() {
                return binding.bind(Evaluable.this.evaluate()).evaluate();
            }

        };

    }

}
