package net.folab.fo.runtime._fo._lang._Boolean._choose;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime._fo._lang._Boolean.choose;
import net.folab.fo.runtime._fo._lang._Boolean._choose.__0._1;

public class _0<T extends Evaluable<T>> extends
        Function<Evaluable<T>, Evaluable<T>> {

    private final choose<T> choose;

    private final Evaluable<T> trueValue;

    public _0(choose<T> choose2, Evaluable<T> trueValue) {
        this.choose = choose2;
        this.trueValue = trueValue;
    }

    @Override
    public Evaluable<T> apply(Evaluable<T> falseValue) {
        return new _1<T>(this, falseValue);
    }

    public choose<T> getChoose() {
        return choose;
    }

    public Evaluable<T> getTrueValue() {
        return trueValue;
    }

}
