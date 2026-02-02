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

import sokeriaaa.return0.shared.common.helpers.TimeHelper
import sokeriaaa.return0.shared.data.api.component.value.Value
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.conditions.EventCondition
import sokeriaaa.return0.shared.data.models.component.values.TimeValue
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import sokeriaaa.return0.shared.data.models.story.event.Event
import sokeriaaa.return0.shared.data.models.story.event.interactive.ItemEntry
import sokeriaaa.return0.shared.data.models.story.event.interactive.shop.ConstructingShopEntry
import sokeriaaa.return0.shared.data.models.story.event.interactive.shop.ShopEntry
import kotlin.jvm.JvmInline

@DslMarker
annotation class ShopDslMarker

@ShopDslMarker
inline fun buildShop(
    key: String,
    builder: ShopBuilder.() -> Unit,
) = Event.Shop(
    key = key,
    entries = buildShopEntries(builder),
)


/**
 * Build a shop entry list with DSL.
 */
@ShopDslMarker
inline fun buildShopEntries(
    builder: ShopBuilder.() -> Unit
): List<ShopEntry> {
    val builder = ShopBuilder()
    builder.builder()
    return builder.entries
}


@JvmInline
@ShopDslMarker
value class ShopBuilder @PublishedApi internal constructor(
    @PublishedApi internal val entries: MutableList<ShopEntry> = mutableListOf()
) : InteractiveBuilder {
    /**
     * Create an inventory item entry.
     */
    @ShopDslMarker
    fun inventory(
        inventoryKey: String,
        amount: Int = 1,
        key: String = "inventory:$inventoryKey",
    ): ShopEntryStarter = ShopEntryStarter(
        ItemEntry.Inventory(
            inventoryKey = inventoryKey,
            amount = amount,
            key = key,
        ),
    )

    /**
     * Create a plugin item entry.
     */
    @ShopDslMarker
    fun plugin(
        pluginKey: String,
        tier: Int,
        amount: Int = 1,
        key: String = "plugin:$pluginKey",
    ): ShopEntryStarter = ShopEntryStarter(
        ItemEntry.Plugin(
            pluginKey = pluginKey,
            tier = tier,
            amount = amount,
            key = key,
        ),
    )

    /**
     * Label the created item entry with the price, then include it into the shop list.
     */
    @ShopDslMarker
    infix fun ShopEntryStarter.soldFor(currencyPair: CurrencyPair): ConstructingShopEntry {
        val entry = ConstructingShopEntry(
            item = item,
            price = currencyPair.currencyPair.first,
            currency = currencyPair.currencyPair.second,
        )
        this@ShopBuilder.entries += entry
        return entry
    }

    /**
     * Limit the count.
     */
    @ShopDslMarker
    infix fun ConstructingShopEntry.limitFor(count: Value.Event): ConstructingShopEntry {
        this.limit = count
        return this
    }

    /**
     * Limit the count.
     */
    @ShopDslMarker
    infix fun ConstructingShopEntry.limitFor(count: Int): ConstructingShopEntry {
        this.limit = Value(count)
        return this
    }

    /**
     * Set the refresh time.
     */
    @ShopDslMarker
    infix fun ConstructingShopEntry.refreshAfter(time: Value.Time): ConstructingShopEntry {
        this.refreshAfter = time
        return this
    }

    /**
     * Set the available condition.
     */
    @ShopDslMarker
    infix fun ConstructingShopEntry.availableWhen(condition: Condition.Event): ConstructingShopEntry {
        this.isAvailable = condition
        return this
    }

    /**
     * A price label for [CurrencyType.TOKEN].
     */
    @ShopDslMarker
    val Value.Event.token: CurrencyPair get() = CurrencyPair(this to CurrencyType.TOKEN)

    /**
     * A price label for [CurrencyType.TOKEN].
     */
    @ShopDslMarker
    val Int.token: CurrencyPair get() = Value(this).token

    /**
     * A price label for [CurrencyType.CRYPTO].
     */
    @ShopDslMarker
    val Value.Event.crypto: CurrencyPair get() = CurrencyPair(this to CurrencyType.CRYPTO)

    /**
     * A price label for [CurrencyType.CRYPTO].
     */
    @ShopDslMarker
    val Int.crypto: CurrencyPair get() = Value(this).crypto

    @ShopDslMarker
    fun addEntry(entry: ShopEntry) {
        entries.add(entry)
    }

    @ShopDslMarker
    fun addEntryCollection(entryCollection: Collection<ShopEntry>) {
        entries.addAll(entryCollection)
    }

    @ShopDslMarker
    fun include(shopBuilder: ShopBuilder) {
        entries.addAll(shopBuilder.entries)
    }

}

@JvmInline
@ShopDslMarker
value class ShopEntryStarter @PublishedApi internal constructor(
    @PublishedApi internal val item: ItemEntry,
)

/**
 * An example of DSL-constructed shop.
 */
private val exampleShop: Event.Shop = buildShop("shop_key") {
    inventory("awesome_item") soldFor 100.token
    inventory("epic_item") soldFor 200.token
    (inventory("legendary_item") soldFor 5.crypto)
        .limitFor(10)
    (plugin("starter_plugin", tier = 1) soldFor 1.crypto)
        .limitFor(20)
        .refreshAfter(TimeValue.After(TimeHelper.ONE_DAY))
        .availableWhen(EventCondition.QuestCompleted("introduce_plugins"))
}
