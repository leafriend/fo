package net.folab.fo.runtime._fo._lang._Boolean;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang._Boolean._choose._0;

public class choose<T extends Evaluable<T>> extends Function<T, Function<T, T>> {

    private final Boolean self;

    public choose(Boolean self) {
        this.self = self;
    }

    @Override
    public _0<T> apply(Evaluable<T> trueValue) {
        return new _0<T>(this, trueValue);
    }

    public Boolean getSelf() {
        return self;
    }

}
