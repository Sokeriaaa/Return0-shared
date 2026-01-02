/**
 * Copyright (C) 2025 Sokeriaaa
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package sokeriaaa.return0.shared.data.api.component.value

import sokeriaaa.return0.shared.data.models.component.values.CommonValue
import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Create a constant value of Int.
 */
fun Value(value: Int) = CommonValue.Constant(value.toFloat())

/**
 * Create a constant value of Float.
 */
fun Value(value: Float) = CommonValue.Constant(value)

/**
 * Create a random value with in [range].
 */
fun Value(range: IntRange) =
    CommonValue.Math.RandomInt(range.first, range.last)

/**
 * Create a random value with in [range].
 */
fun Value(range: ClosedFloatingPointRange<Float>) =
    CommonValue.Math.RandomFloat(range.start, range.endInclusive)

/**
 * Create a random value with in [start]..[endInclusive].
 */
fun Value(start: Int, endInclusive: Int) =
    CommonValue.Math.RandomInt(start, endInclusive)

/**
 * Create a random value with in [start]..<[end].
 */
fun Value(start: Float, end: Float) =
    CommonValue.Math.RandomFloat(start, end)

fun sumOf(vararg values: Value) = CommonValue.Math.Sum(values.asList())

operator fun Value.plus(value2: Value) = when (this) {
    is CommonValue.Constant -> when (value2) {
        is CommonValue.Constant -> Value(
            this.value + value2.value
        )

        is CommonValue.Math.Sum ->
            CommonValue.Math.Sum(listOf(this, *value2.values.toTypedArray()))

        else -> CommonValue.Math.Sum(listOf(this, value2))
    }

    is CommonValue.Math.Sum -> when (value2) {
        is CommonValue.Math.Sum ->
            CommonValue.Math.Sum(this.values + value2.values)

        else -> CommonValue.Math.Sum(this.values + value2)
    }

    else -> when (value2) {
        is CommonValue.Math.Sum ->
            CommonValue.Math.Sum(listOf(this, *value2.values.toTypedArray()))

        else -> CommonValue.Math.Sum(listOf(this, value2))
    }
}

operator fun Value.plus(value2: Float) =
    if (this is CommonValue.Math.Sum) {
        CommonValue.Math.Sum(this.values + Value(value2))
    } else {
        CommonValue.Math.Sum(listOf(this, Value(value2)))
    }

operator fun Value.plus(value2: Int) =
    if (this is CommonValue.Math.Sum) {
        CommonValue.Math.Sum(this.values + Value(value2))
    } else {
        CommonValue.Math.Sum(listOf(this, Value(value2)))
    }

operator fun Value.minus(value2: Float) = this + (-value2)
operator fun Value.minus(value2: Int) = this + (-value2)
operator fun Value.minus(value2: Value) = this + (-value2)

operator fun Value.times(value2: Int) = CommonValue.Math.TimesConst(this, value2.toFloat())
operator fun Value.times(value2: Float) = CommonValue.Math.TimesConst(this, value2)
operator fun Value.times(value2: Value) = CommonValue.Math.Times(this, value2)

operator fun Value.div(value2: Value) = CommonValue.Math.Div(this, value2)
operator fun Int.div(value2: Value) = CommonValue.Math.Div(Value(this), value2)

operator fun Value.unaryMinus() = when (this) {
    is CommonValue.Constant -> Value(-value)
    is CommonValue.Math.TimesConst -> CommonValue.Math.TimesConst(this.value1, -this.value2)
    else -> CommonValue.Math.UnaryMinus(this)
}

infix fun Value.shl(bitCount: Value) = CommonValue.Math.Shift(this, bitCount)
infix fun Value.shr(bitCount: Value) = CommonValue.Math.Shift(this, -bitCount)

infix fun Value.pow(b: Value) = CommonValue.Math.Power(this, b)
infix fun Value.pow(b: Int) = CommonValue.Math.Power(this, Value(b))
infix fun Value.pow(b: Float) = CommonValue.Math.Power(this, Value(b))

val Value.absoluteValue: Value
    get() = abs(this)

fun abs(x: Value) = CommonValue.Math.AbsoluteValue(x)

fun Value.coerceAtLeast(minimumValue: Int) =
    CommonValue.Math.Coerced(this, min = Value(minimumValue))

fun Value.coerceAtLeast(minimumValue: Float) =
    CommonValue.Math.Coerced(this, min = Value(minimumValue))

fun Value.coerceAtLeast(minimumValue: Value) =
    CommonValue.Math.Coerced(this, min = minimumValue)

fun Value.coerceAtMost(maximumValue: Int) =
    CommonValue.Math.Coerced(this, max = Value(maximumValue))

fun Value.coerceAtMost(maximumValue: Float) =
    CommonValue.Math.Coerced(this, max = Value(maximumValue))

fun Value.coerceAtMost(maximumValue: Value) =
    CommonValue.Math.Coerced(this, max = maximumValue)

fun Value.coerceIn(range: IntRange) =
    CommonValue.Math.Coerced(this, min = Value(range.first), max = Value(range.last))

fun Value.coerceIn(range: ClosedFloatingPointRange<Float>) =
    CommonValue.Math.Coerced(this, min = Value(range.start), max = Value(range.endInclusive))

fun minOf(vararg values: Value) = CommonValue.Math.MinOf(values.asList())
fun maxOf(vararg values: Value) = CommonValue.Math.MaxOf(values.asList())
