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
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import kotlin.jvm.JvmInline

interface InteractiveBuilder {
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

}

@JvmInline
@ShopDslMarker
value class CurrencyPair @PublishedApi internal constructor(
    @PublishedApi internal val currencyPair: Pair<Value.Event, CurrencyType>,
)