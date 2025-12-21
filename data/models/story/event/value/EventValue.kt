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
package sokeriaaa.return0.shared.data.models.story.event.value

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType

/**
 * Value components for events.
 */
@Serializable
sealed interface EventValue {
    /**
     * Constant value.
     */
    @Serializable
    @SerialName("Constant")
    data class Constant(val value: Int) : EventValue

    /**
     * Returns a random int in the range.
     */
    @Serializable
    @SerialName("RandomInt")
    data class RandomInt(val start: Int, val endInclusive: Int) : EventValue

    /**
     * Returns a saved value with specified key.
     */
    @Serializable
    @SerialName("SavedVariable")
    data class SavedVariable(val key: String, val default: EventValue = Constant(0)) : EventValue

    /**
     * Returns the currency user have.
     */
    @Serializable
    @SerialName("Currency")
    data class Currency(val type: CurrencyType) : EventValue

    /**
     * Returns the current inventory of the specified item.
     */
    @Serializable
    @SerialName("Inventory")
    data class Inventory(val key: String) : EventValue
}