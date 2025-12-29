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
package sokeriaaa.return0.shared.data.models.story.inventory

import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.story.event.Event

/**
 * A data class for item.
 *
 * @param types Types of this item.
 * @param isConsumedAfterUse This item wil be consumed after used if this param is true.
 * @param useInCombat This item will be executed as an action, then apply this extra to the target.
 *                    `null` means this item cannot be used in a combat.
 * @param useInMap This item will be executed as an event, then this event will be executed.
 *                 `null` means this item cannot be used in the map.
 */
@Serializable
data class ItemData(
    val key: String,
    val nameRes: String,
    val descriptionRes: String,
    val types: List<Type>,
    val isConsumedAfterUse: Boolean = true,
    val useInCombat: Extra? = null,
    val useInMap: Event? = null,
) {

    enum class Type {
        CONSUMABLE,
        MATERIAL,
        QUEST,
        OTHER,
    }

}