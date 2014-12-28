package net.folab.fo.bytecode;

import java.util.ArrayList;
import java.util.List;

public class StatementContext {

    private final Counter statckCounter = new Counter();

    private final Counter localCoutner = new Counter();

    private final List<String> localNames = new ArrayList<String>();

    public StatementContext incStack() {
        statckCounter.inc();
        return this;
    }

    public StatementContext decStack() {
        statckCounter.dec();
        return this;
    }

    public StatementContext clearStack() {
        statckCounter.clear();
        return this;
    }

    public int maxStack() {
        return statckCounter.max();
    }

    public StatementContext addLocal(String name) {
        localCoutner.inc();
        localNames.add(name);
        return this;
    }

    public int indexOfLocal(String name) {
        return localNames.indexOf(name);
    }

    public int maxLocals() {
        return localCoutner.max();
    }

}
