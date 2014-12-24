package net.folab.fo.runtime._fo._lang;

import net.folab.fo.runtime._fo._lang._Boolean.negate;

import net.folab.fo.runtime.Evaluable;
import net.folab.fo.runtime.Value;
import net.folab.fo.runtime._fo._lang._Boolean.choose;

public class Boolean extends Value<Boolean> {

    public static final Boolean FALSE = new Boolean(false);

    public static final Boolean TRUE = new Boolean(true);

    private final boolean value;

    public final <T extends Evaluable<T>> choose<T> choose() {
        return new choose<T>(this);
    }

    public final negate negate = new negate(this);

    private Boolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
