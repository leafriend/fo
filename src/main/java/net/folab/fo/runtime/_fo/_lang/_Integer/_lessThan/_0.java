package net.folab.fo.runtime._fo._lang._Integer._lessThan;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang.Integer;
import net.folab.fo.runtime._fo._lang._Integer.lessThan;

public class _0 extends Evaluable<Boolean> {

    private final lessThan self;

    private final Evaluable<Integer> param;

    public _0(lessThan lessThan, Evaluable<Integer> param) {
        this.self = lessThan;
        this.param = param;
    }

    @Override
    public Boolean evaluate() {
        int a0 = self.getSelf().evaluate().getValue();
        int a1 = param.evaluate().getValue();
        return a0 < a1 ? Boolean.TRUE : Boolean.FALSE;
    }

}
