package net.folab.fo.runtime._fo._lang._Integer;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Function;
import net.folab.fo.runtime._fo._lang.Integer;
import net.folab.fo.runtime._fo._lang._Integer._add._0;

public class add extends Function<Integer, Integer> {

    private final Evaluable<Integer> self;

    public add(Evaluable<Integer> self) {
        this.self = self;
    }

    @Override
    public _0 apply(final Evaluable<Integer> param) {
        return new _0(this, param);
    }

    public Evaluable<Integer> getSelf() {
        return self;
    }

}
