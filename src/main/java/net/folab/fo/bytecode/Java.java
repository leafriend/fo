package net.folab.fo.bytecode;

import org.objectweb.asm.Opcodes;

public enum Java {

    V1_5(Opcodes.V1_5),

    ;

    public final int version;

    private Java(int version) {
        this.version = version;
    }

}
