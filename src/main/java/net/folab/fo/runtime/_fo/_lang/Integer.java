package net.folab.fo.runtime._fo._lang;

import java.util.HashMap;
import java.util.Map;

import net.folab.fo.runtime.Value;
import net.folab.fo.runtime._fo._lang._Integer.add;
import net.folab.fo.runtime._fo._lang._Integer.lessThan;

public class Integer extends Value<Integer> {

    private static Map<java.lang.Integer, Integer> INSTANCES = new HashMap<java.lang.Integer, Integer>();

    private final int value;

    public final add _2B = new add(this);

    public final lessThan _3B = new lessThan(this);

    private Integer(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static Integer valueOf(int i) {
        java.lang.Integer I = java.lang.Integer.valueOf(i);
        if (INSTANCES.containsKey(I))
            return INSTANCES.get(I);
        Integer value = new Integer(i);
        INSTANCES.put(I, value);
        return value;
    }

    public int getValue() {
        return value;
    }

}
