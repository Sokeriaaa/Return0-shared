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
package sokeriaaa.return0.shared.data.api.component.condition

import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.extras.CommonExtra
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.component.values.CommonValue
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.event.Event

fun IF(condition: Condition) =
    ConditionExecutor(condition)

@JvmInline
value class ConditionExecutor @PublishedApi internal constructor(val condition: Condition) {
    /**
     * Value
     */
    fun then(
        ifTrue: Value? = null,
        ifFalse: Value? = null,
        defaultValue: Value? = null,
    ): Value = CommonValue.Conditioned(
        condition = condition,
        ifTrue = ifTrue,
        ifFalse = ifFalse,
        defaultValue = defaultValue
    )

    /**
     * Extra
     */
    fun then(
        ifTrue: Extra? = null,
        ifFalse: Extra? = null
    ): Extra = CommonExtra.Conditioned(
        condition = condition,
        ifTrue = ifTrue,
        ifFalse = ifFalse,
    )

    /**
     * Event
     */
    fun then(
        ifTrue: Event? = null,
        ifFalse: Event? = null
    ): Event = Event.Conditioned(
        condition = condition,
        ifTrue = ifTrue,
        ifFalse = ifFalse,
    )
}
