package net.folab.fo.ast;

import net.folab.metaj.bytecode.Access;
import net.folab.metaj.bytecode.JavaType;

public class MemberDeclaration {

    public final Access access;

    public final JavaType type;

    public final String name;

    public MemberDeclaration(Access access, JavaType type, String name) {
        this.access = access;
        this.type = type;
        this.name = name;
    }

}
