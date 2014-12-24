package net.folab.fo.runtime._fo._lang._Boolean._choose.__0;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime._fo._lang._Boolean._choose._0;

public class _1<T extends Evaluable<T>> extends Evaluable<T> {

    private final _0<T> _0;

    private final Evaluable<T> falseValue;

    public _1(_0<T> _0, Evaluable<T> falseValue) {
        this._0 = _0;
        this.falseValue = falseValue;
    }

    public T evaluate() {
        if (_0.getChoose().getSelf().evaluate().getValue()) {
            return _0.getTrueValue().evaluate();
        } else {
            return falseValue.evaluate();
        }
    }

}
