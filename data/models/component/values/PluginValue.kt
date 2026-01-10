package sokeriaaa.return0.shared.data.models.component.values

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Plugin")
sealed interface PluginValue : Value.Combat {

    /**
     * Tier of equipped plugin.
     */
    @Serializable
    @SerialName("Plugin.Tier")
    data object Tier : PluginValue

}