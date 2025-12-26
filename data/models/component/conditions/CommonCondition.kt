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
package sokeriaaa.return0.shared.data.models.component.conditions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.api.component.value.Value
import sokeriaaa.return0.shared.data.models.component.common.Comparator
import sokeriaaa.return0.shared.data.models.component.values.Value

@Serializable
@SerialName("Common")
sealed interface CommonCondition : Condition.Combat, Condition.Event {

    /**
     * Simple "AND" condition.
     */
    @Serializable
    @SerialName("Common.And")
    data class And(val conditions: List<Condition>) : CommonCondition {
        constructor(vararg conditions: Condition) : this(conditions.toList())
    }

    /**
     * Simple "OR" condition.
     */
    @Serializable
    @SerialName("Common.Or")
    data class Or(val conditions: List<Condition>) : CommonCondition {
        constructor(vararg conditions: Condition) : this(conditions.toList())
    }

    /**
     * Simple "NOT" condition.
     */
    @Serializable
    @SerialName("Common.Not")
    data class Not(val condition: Condition) : CommonCondition

    /**
     * Succeed when [value1] greater (or equal) than [value2].
     */
    @Deprecated("Use CompareValues instead. Will be removed in future.")
    @Serializable
    @SerialName("Common.Compare")
    data class Compare(
        val value1: Value,
        val value2: Value,
        val isIncludeEquals: Boolean,
    ) : CommonCondition

    /**
     * Compare [value1] and [value2] with the [comparator]
     */
    @Serializable
    @SerialName("Common.CompareValues")
    data class CompareValues(
        val value1: Value,
        val comparator: Comparator,
        val value2: Value,
    ) : CommonCondition

    /**
     * A condition returns true with chance of [success]/[base], otherwise false.
     */
    @Serializable
    @SerialName("Common.Chance")
    data class Chance(
        val success: Value,
        val base: Value = Value(1),
    ) : CommonCondition {
        constructor(success: Int, base: Int = 1) : this(Value(success), Value(base))
        constructor(success: Float, base: Float = 1F) : this(Value(success), Value(base))
    }

    /**
     * A condition always returns *true*.
     * Mainly for default value or testing purposes.
     */
    @Serializable
    @SerialName("Common.True")
    data object True : CommonCondition

    /**
     * A condition always returns *false*.
     * Mainly for default value or testing purposes.
     */
    @Serializable
    @SerialName("Common.False")
    data object False : CommonCondition
}
