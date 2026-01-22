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
package sokeriaaa.return0.shared.data.models.story.event.interactive.workbench

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import sokeriaaa.return0.shared.data.models.story.event.interactive.ItemEntry

@Serializable
@SerialName("SimpleWorkbenchEntry")
data class SimpleWorkbenchEntry(
    override val input: List<ItemEntry>,
    override val output: List<ItemEntry>,
    override val price: Value.Event? = null,
    override val currency: CurrencyType? = null,
    override val isAvailable: Condition.Event,
    override val limit: Value.Event?,
    override val refreshAfter: Value.Time?
) : WorkbenchEntry {

    init {
        require(currency != null || price == null) {
            "When price is not null, the currency must not be null."
        }
    }

}
