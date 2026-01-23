package sokeriaaa.return0.shared.data.models.story.event.interactive.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.api.story.event.interactive.ShopDslMarker
import sokeriaaa.return0.shared.data.models.component.conditions.CommonCondition
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import sokeriaaa.return0.shared.data.models.story.event.interactive.ItemEntry

@ConsistentCopyVisibility
@ShopDslMarker
@Serializable
@SerialName("ConstructingShopEntry")
data class ConstructingShopEntry @PublishedApi internal constructor(
    override val item: ItemEntry,
    override val price: Value.Event,
    override val currency: CurrencyType
) : ShopEntry {
    override var isAvailable: Condition.Event = CommonCondition.True
        internal set
    override var limit: Value.Event? = null
        internal set
    override var refreshAfter: Value.Time? = null
        internal set
}