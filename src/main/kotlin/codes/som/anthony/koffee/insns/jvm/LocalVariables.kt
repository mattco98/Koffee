package codes.som.anthony.koffee.insns.jvm

import codes.som.anthony.koffee.insns.InstructionAssembly
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.tree.VarInsnNode

fun InstructionAssembly.aload(slot: Int) {
    instructions.add(VarInsnNode(ALOAD, slot))
}
val InstructionAssembly.aload_0: U get() = aload(0)
val InstructionAssembly.aload_1: U get() = aload(1)
val InstructionAssembly.aload_2: U get() = aload(2)
val InstructionAssembly.aload_3: U get() = aload(3)

fun InstructionAssembly.astore(slot: Int) {
    instructions.add(VarInsnNode(ASTORE, slot))
}
val InstructionAssembly.astore_0: U get() = astore(0)
val InstructionAssembly.astore_1: U get() = astore(1)
val InstructionAssembly.astore_2: U get() = astore(2)
val InstructionAssembly.astore_3: U get() = astore(3)

fun InstructionAssembly.dload(slot: Int) {
    instructions.add(VarInsnNode(DLOAD, slot))
}
val InstructionAssembly.dload_0: U get() = dload(0)
val InstructionAssembly.dload_1: U get() = dload(1)
val InstructionAssembly.dload_2: U get() = dload(2)
val InstructionAssembly.dload_3: U get() = dload(3)

fun InstructionAssembly.dstore(slot: Int) {
    instructions.add(VarInsnNode(DSTORE, slot))
}
val InstructionAssembly.dstore_0: U get() = dstore(0)
val InstructionAssembly.dstore_1: U get() = dstore(1)
val InstructionAssembly.dstore_2: U get() = dstore(2)
val InstructionAssembly.dstore_3: U get() = dstore(3)

fun InstructionAssembly.fload(slot: Int) {
    instructions.add(VarInsnNode(FLOAD, slot))
}
val InstructionAssembly.fload_0: U get() = fload(0)
val InstructionAssembly.fload_1: U get() = fload(1)
val InstructionAssembly.fload_2: U get() = fload(2)
val InstructionAssembly.fload_3: U get() = fload(3)

fun InstructionAssembly.fstore(slot: Int) {
    instructions.add(VarInsnNode(FSTORE, slot))
}
val InstructionAssembly.fstore_0: U get() = fstore(0)
val InstructionAssembly.fstore_1: U get() = fstore(1)
val InstructionAssembly.fstore_2: U get() = fstore(2)
val InstructionAssembly.fstore_3: U get() = fstore(3)

fun InstructionAssembly.iload(slot: Int) {
    instructions.add(VarInsnNode(ILOAD, slot))
}
val InstructionAssembly.iload_0: U get() = iload(0)
val InstructionAssembly.iload_1: U get() = iload(1)
val InstructionAssembly.iload_2: U get() = iload(2)
val InstructionAssembly.iload_3: U get() = iload(3)

fun InstructionAssembly.istore(slot: Int) {
    instructions.add(VarInsnNode(ISTORE, slot))
}
val InstructionAssembly.istore_0: U get() = istore(0)
val InstructionAssembly.istore_1: U get() = istore(1)
val InstructionAssembly.istore_2: U get() = istore(2)
val InstructionAssembly.istore_3: U get() = istore(3)

fun InstructionAssembly.lload(slot: Int) {
    instructions.add(VarInsnNode(LLOAD, slot))
}
val InstructionAssembly.lload_0: U get() = lload(0)
val InstructionAssembly.lload_1: U get() = lload(1)
val InstructionAssembly.lload_2: U get() = lload(2)
val InstructionAssembly.lload_3: U get() = lload(3)

fun InstructionAssembly.lstore(slot: Int) {
    instructions.add(VarInsnNode(LSTORE, slot))
}
val InstructionAssembly.lstore_0: U get() = lstore(0)
val InstructionAssembly.lstore_1: U get() = lstore(1)
val InstructionAssembly.lstore_2: U get() = lstore(2)
val InstructionAssembly.lstore_3: U get() = lstore(3)
