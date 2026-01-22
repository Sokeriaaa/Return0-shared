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
import sokeriaaa.return0.shared.data.models.component.conditions.CommonCondition
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import sokeriaaa.return0.shared.data.models.story.event.interactive.ItemEntry

@ConsistentCopyVisibility
@Serializable
@SerialName("ConstructingWorkbenchEntry")
data class ConstructingWorkbenchEntry internal constructor(
    override val input: List<ItemEntry>,
    override val output: List<ItemEntry>
) : WorkbenchEntry {
    override var price: Value.Event? = null
        internal set
    override var currency: CurrencyType? = null
        internal set
    override var isAvailable: Condition.Event = CommonCondition.True
        internal set
    override var limit: Value.Event? = null
        internal set
    override var refreshAfter: Value.Time? = null
        internal set
}