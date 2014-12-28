package net.folab.fo.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Block {

    public final List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    public Block addStatement(Statement statement) {
        List<Statement> statements = new ArrayList<Statement>(this.statements);
        statements.add(statement);
        return new Block(statements);
    }

}
