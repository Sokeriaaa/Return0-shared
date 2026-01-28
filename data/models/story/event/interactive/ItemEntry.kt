/**
 * Copyright (C) 2026 Sokeriaaa
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
package sokeriaaa.return0.shared.data.models.story.event.interactive

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An item entry with the item info and the amount.
 *
 * Used for shops and workbenches.
 */
@Serializable
sealed interface ItemEntry {
    /**
     * The key of this entry, used for calculating refresh time.
     *
     * The full shop should not have two identical keys with both have refreshing time.
     * Default is inventoryKey/pluginKey
     */
    val key: String

    /**
     * Amount of items in this entry.
     */
    val amount: Int

    /**
     * Resource key for displaying the name.
     */
    val resourceKey: String

    /**
     * Resource key for displaying the description.
     */
    val resourceDescKey: String

    /**
     * An inventory item.
     */
    @Serializable
    @SerialName("Inventory")
    data class Inventory(
        val inventoryKey: String,
        override val amount: Int = 1,
        override val key: String = "inventory:$inventoryKey",
    ) : ItemEntry {
        override val resourceKey: String = "inventory.${inventoryKey}"
        override val resourceDescKey: String = "inventory.${inventoryKey}.desc"
    }

    /**
     * A plugin item.
     *
     * @param pluginKey Plugin key. When `null` is used here, it presents a random
     *                  output.
     * @param tier Plugin tier, can be 0..5. 0 presents for random tier, other values
     *             presents their exact value. When used for input, plugins with any
     *             tier can be used.
     */
    @Serializable
    @SerialName("Plugin")
    data class Plugin(
        val pluginKey: String?,
        val tier: Int = 0,
        override val amount: Int = 1,
        override val key: String = "plugin:$pluginKey",
    ) : ItemEntry {
        init {
            require(tier in 0..5) {
                "Tier must be between 0 and 5, current: $tier"
            }
        }

        override val resourceKey: String = "plugin.${pluginKey}"
        override val resourceDescKey: String = "plugin.${pluginKey}.desc"
    }
}