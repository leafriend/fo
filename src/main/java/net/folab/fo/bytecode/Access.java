package net.folab.fo.bytecode;

import org.objectweb.asm.Opcodes;

public enum Access {

    PUBLIC(Opcodes.ACC_PUBLIC),

    PROTECTED(Opcodes.ACC_PROTECTED),

    DEFAULT(0),

    PRIVATE(Opcodes.ACC_PRIVATE)

    ;

    public final int modifier;

    private Access(int modifier) {
        this.modifier = modifier;
    }

}
