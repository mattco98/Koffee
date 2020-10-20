package codes.som.anthony.koffee.insns.sugar

import codes.som.anthony.koffee.MethodAssembly
import codes.som.anthony.koffee.insns.jvm.*

fun MethodAssembly.aload(local: Local) {
    aload(local.index)
}

fun MethodAssembly.astore(): Local {
    astore(currentLocalIndex)
    return Local(currentLocalIndex++, LocalType.Object)
}

fun MethodAssembly.dload(local: Local) {
    dload(local.index)
}

fun MethodAssembly.dstore(): Local {
    dstore(currentLocalIndex)
    return Local(currentLocalIndex++, LocalType.Double)
}

fun MethodAssembly.fload(local: Local) {
    fload(local.index)
}

fun MethodAssembly.fstore(): Local {
    fstore(currentLocalIndex)
    return Local(currentLocalIndex++, LocalType.Float)
}

fun MethodAssembly.iload(local: Local) {
    iload(local.index)
}

fun MethodAssembly.istore(): Local {
    istore(currentLocalIndex)
    return Local(currentLocalIndex++, LocalType.Int)
}

fun MethodAssembly.lload(local: Local) {
    lload(local.index)
}

fun MethodAssembly.lstore(): Local {
    lstore(currentLocalIndex)
    return Local(currentLocalIndex++, LocalType.Long)
}

fun MethodAssembly.load(local: Local) {
    when (local.type) {
        LocalType.Object -> aload(local)
        LocalType.Float -> fload(local)
        LocalType.Int -> iload(local)
        LocalType.Double -> dload(local)
        LocalType.Long -> lload(local)
    }
}

data class Local(val index: Int, val type: LocalType)

enum class LocalType {
    Object,
    Float,
    Int,
    Double,
    Long
}
