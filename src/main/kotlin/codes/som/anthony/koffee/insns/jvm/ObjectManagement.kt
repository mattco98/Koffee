package codes.som.anthony.koffee.insns.jvm

import codes.som.anthony.koffee.insns.InstructionAssembly
import codes.som.anthony.koffee.types.TypeLike
import codes.som.anthony.koffee.types.coerceType
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.tree.TypeInsnNode

fun InstructionAssembly.new(type: TypeLike) {
    instructions.add(TypeInsnNode(NEW, coerceType(type).internalName))
}
inline fun <reified T> InstructionAssembly.new() {
    new(T::class)
}
fun InstructionAssembly.checkcast(type: TypeLike) {
    instructions.add(TypeInsnNode(CHECKCAST, coerceType(type).internalName))
}
inline fun <reified T> InstructionAssembly.checkcast() {
    checkcast(T::class)
}
fun InstructionAssembly.instanceof(type: TypeLike) {
    instructions.add(TypeInsnNode(INSTANCEOF, coerceType(type).internalName))
}
inline fun <reified T> InstructionAssembly.instanceof() {
    instanceof(T::class)
}
