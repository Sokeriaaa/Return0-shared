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
package sokeriaaa.return0.shared.data.models.component.extras

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value

@Serializable
@SerialName("Common")
sealed interface CommonExtra : Extra {

    /**
     * An empty action extra does nothing.
     */
    @Serializable
    @SerialName("Common.Empty")
    data object Empty : CommonExtra

    /**
     * Execute the [condition] and depending the result to execute [ifTrue] or [ifFalse].
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Conditioned")
    data class Conditioned internal constructor(
        val condition: Condition,
        val ifTrue: Extra? = null,
        val ifFalse: Extra? = null,
    ) : CommonExtra

    /**
     * A group of extras, all extra will be executed one by one.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Grouped")
    data class Grouped internal constructor(val extras: List<Extra>) : CommonExtra

    /**
     * Temporary calculate a [value] and save the calculated value in a [key] in this **action**,
     *  this value can be read in
     *  [sokeriaaa.return0.shared.data.models.component.values.CommonValue.LoadValue].
     *
     * The [value] is calculated immediately and become frozen as soon as this extra is executed.
     *
     * All values will be cleared when the entity is failed,
     *  turns of the effect is run out or combat is finished.
     *
     * @see sokeriaaa.return0.shared.data.models.component.values.CommonValue.LoadValue
     * @see sokeriaaa.return0.models.action.Action.values
     */
    @Serializable
    @SerialName("Common.SaveValue")
    data class SaveValue(val key: String, val value: Value) : CommonExtra

    /**
     * Execute [extra] on the **user** of action.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.ForUser")
    data class ForUser internal constructor(val extra: Extra) : CommonExtra

    /**
     * Execute [extra] but with **the user and the target swapped**.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Common.Swapped")
    data class Swapped internal constructor(val extra: Extra) : CommonExtra
}