package sokeriaaa.return0.shared.data.models.component.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Formatter for displaying the values.
 */
@Serializable
sealed interface Formatter {
    @Serializable
    @SerialName("Integer")
    data object Integer : Formatter

    @Serializable
    @SerialName("Percent")
    data class Percent(val decimal: Int) : Formatter

    @Serializable
    @SerialName("Decimal")
    data class Decimal(val decimal: Int) : Formatter
}