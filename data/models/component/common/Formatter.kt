package sokeriaaa.return0.shared.data.models.component.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.common.helpers.toPrecision

/**
 * Formatter for displaying the values.
 */
@Serializable
sealed interface Formatter {
    @Serializable
    @SerialName("Integer")
    data object Integer : Formatter {
        override fun format(value: Float): String {
            return value.toInt().toString()
        }
    }

    @Serializable
    @SerialName("Percent")
    data class Percent(val decimal: Int) : Formatter {
        override fun format(value: Float): String {
            return (value * 100).toPrecision(decimal) + "%"
        }
    }

    @Serializable
    @SerialName("Decimal")
    data class Decimal(val decimal: Int) : Formatter {
        override fun format(value: Float): String {
            return value.toPrecision(decimal)
        }
    }

    fun format(value: Float): String
}