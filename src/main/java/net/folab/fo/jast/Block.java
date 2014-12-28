package net.folab.fo.jast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Block {

    public final boolean isStatic;

    public final List<Statement> statements;

    public Block(boolean isStatic, List<Statement> statements) {
        this.statements = Collections.unmodifiableList(statements);
        this.isStatic = isStatic;
    }

    public Block addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(this.statements);
        statements.add(statement);
        return new Block(isStatic, statements);
    }

    public Block setStatic(boolean isStatic) {
        return new Block(isStatic, statements);
    }

}
