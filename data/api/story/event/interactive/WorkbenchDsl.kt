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
package sokeriaaa.return0.shared.data.api.story.event.interactive

import sokeriaaa.return0.shared.data.api.component.value.Value
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.event.Event
import sokeriaaa.return0.shared.data.models.story.event.interactive.ItemEntry
import sokeriaaa.return0.shared.data.models.story.event.interactive.workbench.ConstructingWorkbenchEntry
import sokeriaaa.return0.shared.data.models.story.event.interactive.workbench.WorkbenchEntry
import kotlin.jvm.JvmInline

@DslMarker
annotation class WorkbenchDslMarker

@WorkbenchDslMarker
inline fun buildWorkbench(
    key: String? = null,
    builder: WorkbenchBuilder.() -> Unit,
) = Event.Workbench(
    key = key,
    entries = buildWorkbenchEntries(builder),
)

/**
 * Build a shop entry list with DSL.
 */
@WorkbenchDslMarker
inline fun buildWorkbenchEntries(
    builder: WorkbenchBuilder.() -> Unit
): List<WorkbenchEntry> {
    val builder = WorkbenchBuilder()
    builder.builder()
    return builder.entries
}


@JvmInline
@WorkbenchDslMarker
value class WorkbenchBuilder @PublishedApi internal constructor(
    @PublishedApi internal val entries: MutableList<WorkbenchEntry> = mutableListOf()
) : InteractiveBuilder {

    /**
     * Declare the input items.
     */
    @WorkbenchDslMarker
    fun inputting(vararg items: ItemEntry): WorkbenchEntryStarter {
        return WorkbenchEntryStarter(items.toList())
    }

    /**
     * Create an inventory entry.
     */
    @WorkbenchDslMarker
    infix fun Int.of(inventoryKey: String): ItemEntry.Inventory = ItemEntry.Inventory(
        inventoryKey = inventoryKey,
        amount = this
    )

    /**
     * Create a plugin entry with random tier.
     */
    @WorkbenchDslMarker
    infix fun Int.ofPlugin(pluginKey: String): ItemEntry.Plugin = ItemEntry.Plugin(
        pluginKey = pluginKey,
        tier = 0,
        amount = this,
    )

    /**
     * Define a tier.
     */
    @WorkbenchDslMarker
    infix fun ItemEntry.Plugin.withTier(tier: Int): ItemEntry.Plugin =
        this.copy(tier = tier)

    /**
     * Declare the output of inputs and add this entry to the workbench.
     */
    @WorkbenchDslMarker
    infix fun WorkbenchEntryStarter.outputs(entry: ItemEntry): ConstructingWorkbenchEntry {
        val entry = ConstructingWorkbenchEntry(
            input = input,
            output = listOf(entry),
        )
        this@WorkbenchBuilder.entries.add(entry)
        return entry
    }

    /**
     * Declare the output of inputs and add this entry to the workbench.
     */
    @WorkbenchDslMarker
    infix fun WorkbenchEntryStarter.outputs(items: List<ItemEntry>): ConstructingWorkbenchEntry {
        val entry = ConstructingWorkbenchEntry(
            input = input,
            output = items,
        )
        this@WorkbenchBuilder.entries.add(entry)
        return entry
    }

    /**
     * Declare the output of inputs and add this entry to the workbench.
     */
    @WorkbenchDslMarker
    infix fun ConstructingWorkbenchEntry.costsFor(
        currencyPair: CurrencyPair,
    ): ConstructingWorkbenchEntry {
        this.price = currencyPair.currencyPair.first
        this.currency = currencyPair.currencyPair.second
        return this
    }

    /**
     * Limit the count.
     */
    @WorkbenchDslMarker
    infix fun ConstructingWorkbenchEntry.limitFor(count: Value.Event): ConstructingWorkbenchEntry {
        this.limit = count
        return this
    }

    /**
     * Limit the count.
     */
    @WorkbenchDslMarker
    infix fun ConstructingWorkbenchEntry.limitFor(count: Int): ConstructingWorkbenchEntry {
        this.limit = Value(count)
        return this
    }

    /**
     * Set the refresh time.
     */
    @WorkbenchDslMarker
    infix fun ConstructingWorkbenchEntry.refreshAfter(time: Value.Time): ConstructingWorkbenchEntry {
        this.refreshAfter = time
        return this
    }

    /**
     * Set the available condition.
     */
    @WorkbenchDslMarker
    infix fun ConstructingWorkbenchEntry.availableWhen(condition: Condition.Event): ConstructingWorkbenchEntry {
        this.isAvailable = condition
        return this
    }

    @WorkbenchDslMarker
    fun addEntry(entry: WorkbenchEntry) {
        entries.add(entry)
    }

    @WorkbenchDslMarker
    fun addEntryCollection(entryCollection: Collection<WorkbenchEntry>) {
        entries.addAll(entryCollection)
    }

    @WorkbenchDslMarker
    fun include(workbenchBuilder: WorkbenchBuilder) {
        entries.addAll(workbenchBuilder.entries)
    }
}

@JvmInline
@WorkbenchDslMarker
value class WorkbenchEntryStarter @PublishedApi internal constructor(
    @PublishedApi internal val input: List<ItemEntry>
)

/**
 * An example of DSL-constructed workbench.
 */
private val exampleWorkbench: Event.Workbench = buildWorkbench {
    inputting(
        50 of "awesome_item",
        100 of "epic_item",
    ) outputs (1 of "rare_item")
    inputting(
        100 of "awesome_item",
        200 of "epic_item",
    ) outputs listOf(1 of "rare_item", 1 of "legendary_item")

    inputting(
        10 of "plugin_material",
        10 of "another_plugin_material",
    ) outputs (1 ofPlugin "awesome_plugin" withTier 5)
}
