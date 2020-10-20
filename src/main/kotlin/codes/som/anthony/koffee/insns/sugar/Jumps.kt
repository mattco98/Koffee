package codes.som.anthony.koffee.insns.sugar

import codes.som.anthony.koffee.BlockAssembly
import codes.som.anthony.koffee.insns.InstructionAssembly
import codes.som.anthony.koffee.insns.jvm.goto
import codes.som.anthony.koffee.labels.LabelLike
import codes.som.anthony.koffee.labels.LabelScope
import codes.som.anthony.koffee.labels.coerceLabel
import codes.som.anthony.koffee.types.TypeLike
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.tree.InsnList
import org.objectweb.asm.tree.JumpInsnNode
import org.objectweb.asm.tree.LabelNode
import org.objectweb.asm.tree.MethodNode

fun InstructionAssembly.makeLabel() = LabelNode()

fun InstructionAssembly.placeLabel(label: LabelLike) {
    instructions.add(coerceLabel(label))
}

fun InstructionAssembly.jump(condition: JumpCondition, label: TypeLike) {
    instructions.add(JumpInsnNode(condition.opcode, coerceLabel(label)))
}

/**
 * If-statement helper.
 *
 * Note that this helper evaluates it's conditions similar to Java, not
 * like JVM bytecode. In Java, if-statements run their code if the condition
 * is true, whereas in bytecode if-statements _skip_ their code if the
 * condition is true. As the latter is quite unintuitive, that is not the
 * default behavior here. However, it can be enabled by setting the
 * interpretLikeBytecode argument to true
 */
fun InstructionAssembly.ifStatement(vararg conditions: JumpCondition, interpretLikeBytecode: Boolean = false, block: InstructionAssembly.() -> Unit) {
    val label = makeLabel()

    for (condition in conditions) {
        val opcode = if (interpretLikeBytecode) condition.opcode else condition.opposite
        instructions.add(JumpInsnNode(opcode, label))
    }

    this.block()
    placeLabel(label)
}

/**
 * See ifStatement above
 */
fun InstructionAssembly.ifElseStatement(vararg conditions: JumpCondition, interpretLikeBytecode: Boolean = false, block: IfElseBuilder.() -> Unit) {
    val ifElse = IfElseBuilder()
    ifElse.block()
    if (ifElse.ifBlock == null || ifElse.elseBlock == null)
        throw IllegalArgumentException("ifElseStatement requires an if block and and else block")

    val ifLabel = makeLabel()
    val endLabel = makeLabel()

    for (condition in conditions) {
        val opcode = if (interpretLikeBytecode) condition.opcode else condition.opposite
        instructions.add(JumpInsnNode(opcode, ifLabel))
    }

    this.apply(ifElse.ifBlock!!)
    goto(endLabel)
    placeLabel(ifLabel)
    this.apply(ifElse.elseBlock!!)
    placeLabel(endLabel)
}

class IfElseBuilder {
    var ifBlock: (InstructionAssembly.() -> Unit)? = null
    var elseBlock: (InstructionAssembly.() -> Unit)? = null

    fun ifBlock(block: InstructionAssembly.() -> Unit) {
        ifBlock = block
    }

    fun elseBlock(block: InstructionAssembly.() -> Unit) {
        elseBlock = block
    }
}

enum class JumpCondition(val opcode: Int, val opposite: Int) {
    True(IFNE, IFEQ),
    False(IFEQ, IFNE),
    Equal(IFEQ, IFNE),
    NotEqual(IFNE, IFEQ),
    LessThan(IFLT, IFGE),
    GreaterThan(IFGT, IFLE),
    LessThanOrEqual(IFLE, IFGT),
    GreaterThanOrEqual(IFGE, IFLT),
    Null(IFNULL, IFNONNULL),
    NonNull(IFNONNULL, IFNULL),
    RefEqual(IF_ACMPEQ, IF_ACMPNE),
    RefNotEqual(IF_ACMPNE, IF_ACMPEQ),
}
