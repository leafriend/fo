package net.folab.fo.runtime._fo._lang._Boolean;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang._Boolean._choose._0;

public class choose<T extends Evaluable<T>> extends
        Function<Evaluable<T>, Function<Evaluable<T>, Evaluable<T>>> {

    private final Boolean self;

    public choose(Boolean self) {
        this.self = self;
    }

    @Override
    public Function<Evaluable<T>, Evaluable<T>> apply(Evaluable<T> trueValue) {
        return new _0<T>(this, trueValue);
    }

    public Boolean getSelf() {
        return self;
    }

}
