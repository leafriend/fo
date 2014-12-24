package net.folab.fo.runtime._fo._lang._Integer;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang.Integer;
import net.folab.fo.runtime._fo._lang._Integer._lessThan._0;

public class lessThan extends Function<Integer, Boolean> {

    private final Evaluable<Integer> self;

    public lessThan(Evaluable<Integer> self) {
        this.self = self;
    }

    public _0 apply(final Evaluable<Integer> param) {
        return new _0(this, param);
    }

    public Evaluable<Integer> getSelf() {
        return self;
    }

}
