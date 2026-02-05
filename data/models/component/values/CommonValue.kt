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
package sokeriaaa.return0.shared.data.models.component.values

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.common.Formatter
import sokeriaaa.return0.shared.data.models.component.conditions.Condition

@Serializable
@SerialName("Common")
sealed interface CommonValue : Value.Combat, Value.Event, Value.Item {

    /**
     * A simple constant value. Use [sokeriaaa.return0.shared.data.models.component.values.Value] to create.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Constant")
    data class Constant internal constructor(
        val value: Float,
        val formatter: Formatter,
    ) : CommonValue

    /**
     * Math operations
     */
    @Serializable
    @SerialName("Common.Math")
    sealed interface Math : CommonValue {
        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Sum")
        data class Sum internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Times")
        data class Times internal constructor(val value1: Value, val value2: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.TimesConst")
        data class TimesConst internal constructor(val value1: Value, val value2: Float) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Div")
        data class Div internal constructor(val value1: Value, val value2: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.UnaryMinus")
        data class UnaryMinus internal constructor(val value1: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Shift")
        data class Shift internal constructor(val value1: Value, val shift: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Power")
        data class Power internal constructor(val value1: Value, val value2: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.AbsoluteValue")
        data class AbsoluteValue internal constructor(val value: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.Coerced")
        data class Coerced internal constructor(
            val value: Value,
            val min: Value? = null,
            val max: Value? = null,
        ) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.MinOf")
        data class MinOf internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.MaxOf")
        data class MaxOf internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.RandomInt")
        data class RandomInt internal constructor(
            val start: Int,
            val endInclusive: Int,
        ) : CommonValue

        @ConsistentCopyVisibility
        @Serializable
        @SerialName("Common.Math.RandomFloat")
        data class RandomFloat internal constructor(
            val start: Float,
            val end: Float,
            val formatter: Formatter,
        ) : CommonValue
    }

    /**
     * Execute the [condition] and depending the result to use [ifTrue] or [ifFalse].
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Conditioned")
    data class Conditioned internal constructor(
        val condition: Condition,
        val ifTrue: Value? = null,
        val ifFalse: Value? = null,
        val defaultValue: Value? = null,
    ) : CommonValue

    /**
     * Check the conditions in [cases] one by one, if `true`, return the value and stop
     *  calculating. If all conditions are `false`, return the [defaultValue].
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Cased")
    data class Cased @PublishedApi internal constructor(
        val cases: Map<Condition, Value?>,
        val defaultValue: Value? = null,
    ) : CommonValue

}
