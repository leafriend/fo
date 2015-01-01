package net.folab.fo.jast;

import net.folab.fo.bytecode.Access;
import net.folab.fo.bytecode.JavaType;

public class MemberDeclaration {

    public final Access access;

    public final JavaType type;

    public final String name;

    public MemberDeclaration(Access access, JavaType type, String name) {
        this.access = access;
        this.type = type;
        this.name = name;
    }

    public void accept(AstVisitor av) {
        av.visitField(this);
    }

}
