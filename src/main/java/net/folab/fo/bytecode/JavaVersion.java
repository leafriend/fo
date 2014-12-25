package net.folab.fo.bytecode;

import org.objectweb.asm.Opcodes;

public enum JavaVersion {

    V1_5(Opcodes.V1_5),

    ;

    public final int version;

    private JavaVersion(int version) {
        this.version = version;
    }

}
