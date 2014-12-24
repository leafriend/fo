package net.folab.fo.runtime._fo._lang._Boolean;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime._fo._lang.Boolean;

public class negate extends Evaluable<Boolean> {

    private final Boolean self;

    public negate(Boolean self) {
        this.self = self;
    }

    @Override
    public Boolean evaluate() {
        if (self.getValue()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

}
