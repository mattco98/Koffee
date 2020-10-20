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

fun InstructionAssembly.ifStatement(vararg conditions: JumpCondition, block: InstructionAssembly.() -> Unit) {
    val label = makeLabel()

    for (condition in conditions)
        jump(condition, label)

    this.block()
    placeLabel(label)
}

fun InstructionAssembly.ifElseStatement(vararg conditions: JumpCondition, block: IfElseBuilder.() -> Unit) {
    val ifElse = IfElseBuilder()
    ifElse.block()
    if (ifElse.ifBlock == null || ifElse.elseBlock == null)
        throw IllegalArgumentException("ifElseStatement requires an if block and and else block")

    val ifLabel = makeLabel()
    val endLabel = makeLabel()

    for (condition in conditions)
        jump(condition, ifLabel)

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

enum class JumpCondition(val opcode: Int) {
    True(IFNE),
    False(IFEQ),
    Equal(IFEQ),
    NotEqual(IFNE),
    LessThan(IFLT),
    GreaterThan(IFGT),
    LessThanOrEqual(IFLE),
    GreaterThanOrEqual(IFGE),
    Null(IFNULL),
    NonNull(IFNONNULL),
    Goto(GOTO),
    RefEqual(IF_ACMPEQ),
    RefNotEqual(IF_ACMPNE),
}
