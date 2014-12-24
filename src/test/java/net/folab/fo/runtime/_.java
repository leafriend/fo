package net.folab.fo.runtime;

import net.folab.fo.runtime._fo._lang.Boolean;
import net.folab.fo.runtime._fo._lang.Integer;

public class _ {

    // a = 1
    public static final Integer _a = Integer.valueOf(1);

    // b = 2
    public static final Integer _b = Integer.valueOf(2);

    // c = a + b # = 1 + 2 = 3
    public static final Evaluable<Integer> _c = _a._2B.apply(_b);

    // inc = 1 +
    public static final Function<Integer, Integer> _inc = Integer.valueOf(1)._2B;

    // d = 4
    public static final Integer _d = Integer.valueOf(4);

    // e = inc d # = 1 + 4 = 5
    public static final Evaluable<Integer> _e = _inc.apply(_d);

    // f = 10 < 7 = false
    public static final Evaluable<Boolean> _f = Integer.valueOf(10)._3B
            .apply(Integer.valueOf(7));

}
