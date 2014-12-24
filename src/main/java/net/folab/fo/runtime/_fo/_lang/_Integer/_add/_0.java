package net.folab.fo.runtime._fo._lang._Integer._add;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime._fo._lang.Integer;
import net.folab.fo.runtime._fo._lang._Integer.add;

public class _0 extends Evaluable<Integer> {

    private final add self;

    private final Evaluable<Integer> param;

    public _0(add fo_lang_int_add,
            Evaluable<Integer> param) {
        this.self = fo_lang_int_add;
        this.param = param;
    }

    public Integer evaluate() {
        int a0 = self.getSelf().evaluate().getValue();
        int a1 = param.evaluate().getValue();
        Integer r = Integer.valueOf(a0 + a1);
        return r;
    }

}
