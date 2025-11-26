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

import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.conditions.Condition

@Serializable
sealed interface CommonValue : Value {

    /**
     * A simple constant value. Use [sokeriaaa.return0.shared.data.models.component.values.Value] to create.
     */
    @ConsistentCopyVisibility
    @Serializable
    data class Constant internal constructor(val value: Float) : CommonValue

    /**
     * Math operations
     */
    @Serializable
    sealed interface Math : CommonValue {
        @ConsistentCopyVisibility
        @Serializable
        data class Sum internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class Times internal constructor(val value1: Value, val value2: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class TimesConst internal constructor(val value1: Value, val value2: Float) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class Div internal constructor(val value1: Value, val value2: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class UnaryMinus internal constructor(val value1: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class Shift internal constructor(val value1: Value, val shift: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class AbsoluteValue internal constructor(val value: Value) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class Coerced internal constructor(
            val value: Value,
            val min: Value? = null,
            val max: Value? = null,
        ) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class MinOf internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class MaxOf internal constructor(val values: List<Value>) : Math

        @ConsistentCopyVisibility
        @Serializable
        data class RandomInt internal constructor(
            val start: Int,
            val endInclusive: Int,
        ) : CommonValue

        @ConsistentCopyVisibility
        @Serializable
        data class RandomFloat internal constructor(
            val start: Float,
            val end: Float,
        ) : CommonValue
    }

    /**
     * Execute the [condition] and depending the result to use [ifTrue] or [ifFalse].
     */
    @ConsistentCopyVisibility
    @Serializable
    data class Conditioned internal constructor(
        val condition: Condition,
        val ifTrue: Value? = null,
        val ifFalse: Value? = null,
        val defaultValue: Value? = null,
    ) : CommonValue

    /**
     * Load a value in the action saved by
     *  [sokeriaaa.return0.shared.data.models.component.extras.CommonExtra.SaveValue].
     *
     * @see sokeriaaa.return0.shared.data.models.component.extras.CommonExtra.SaveValue
     * @see sokeriaaa.return0.models.action.Action.values
     */
    @Serializable
    data class LoadValue(
        val key: String,
        val defaultValue: Value? = null,
    ) : CommonValue

}
