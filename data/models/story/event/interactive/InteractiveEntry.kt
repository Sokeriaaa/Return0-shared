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

import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType

interface InteractiveEntry {
    /**
     * The price of this item. `null` means this entry is free to be purchased/crafted.
     *
     * For the shop, this value typically should be non-null and above 0.
     */
    val price: Value.Event?

    /**
     * Currency type. Must not be `null` when [price] is not `null`.
     */
    val currency: CurrencyType?

    /**
     * Whether this entry is available.
     */
    val isAvailable: Condition.Event

    /**
     * The limit of this item. `null` means not limited, the player can purchase/craft
     * as much as they can.
     */
    val limit: Value.Event?

    /**
     * If limit is not null, the stock will be fully restored after this value of
     * millis when the player purchased/crafted at least one item.
     *
     * This value is calculated and stored locally once the player purchase/crafted
     * at least one item from this entry.
     *
     * Have no effect when [limit] is null.
     */
    val refreshAfter: Value.Time?
}