package net.folab.fo.runtime;

public abstract class Evaluable<T extends Evaluable<T>> {

    public abstract T evaluate();

    public <P extends Evaluable<P>, R extends Evaluable<R>> Function<P, R> bind(
            final Binding<T, P, R> binding) {

        return new Function<P, R>() {

            @Override
            public Evaluable<R> apply(final Evaluable<P> parameter) {

                return new Evaluable<R>() {

                    @Override
                    public R evaluate() {
                        return binding.bind(Evaluable.this.evaluate()).apply(parameter).evaluate();
                    }

                };
            }

            @Override
            public Function<P, R> evaluate() {
                return binding.bind(Evaluable.this.evaluate());
            }

        };

    }

}
