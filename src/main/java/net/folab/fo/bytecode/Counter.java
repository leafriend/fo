package net.folab.fo.bytecode;

public class Counter {

    private int counter;

    private int max;

    public Counter() {
        counter = 0;
        max = 0;
    }

    public Counter inc() {
        counter++;
        max = Math.max(max, counter);
        return this;
    }

    public Counter dec() {
        counter--;
        return this;
    }

    public Counter clear() {
        counter = 0;
        return this;
    }

    public int max() {
        return max;
    }

}
