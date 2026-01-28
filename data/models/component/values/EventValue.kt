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
import sokeriaaa.return0.shared.data.api.component.value.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType

sealed interface EventValue : Value.Event {

    /**
     * Returns a saved value with specified key.
     */
    @Serializable
    @SerialName("Event.SavedVariable")
    data class SavedVariable(val key: String, val default: Value.Event = Value(0)) : EventValue

    /**
     * Returns the currency user have.
     */
    @Serializable
    @SerialName("Event.Currency")
    data class Currency(val currencyType: CurrencyType) : EventValue

    /**
     * Returns the current inventory of the specified item.
     */
    @Serializable
    @SerialName("Event.Inventory")
    data class Inventory(val key: String) : EventValue

    /**
     * Returns the ordinal of the player's current title (starts from 0).
     */
    @Serializable
    @SerialName("Event.TitleOrdinal")
    data object TitleOrdinal : EventValue

    /**
     * Returns an enemy level based on the player's current title.
     * This value should only be used for starting combats and be filled in the level slots.
     *
     * @param difficulty The difficulty of the map.
     * @param offset The offset of value. Final level will be between level-offset and
     *               level+offset with a minimum value 1. Default is 0.
     */
    @Serializable
    @SerialName("Event.EnemyLevel")
    data class EnemyLevel(val difficulty: Int, val offset: Int = 0) : EventValue
}