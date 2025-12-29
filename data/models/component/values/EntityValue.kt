/**
 * Copyright (C) 2025 Sokeriaaa
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
package sokeriaaa.return0.shared.data.models.component.values

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data of the target. Can be wrapped with forUser to apply for user.
 */
@Serializable
@SerialName("Entity")
sealed interface EntityValue : Value.Combat, Value.Item {
    @Serializable
    @SerialName("Entity.ATK")
    data object ATK : EntityValue

    @Serializable
    @SerialName("Entity.DEF")
    data object DEF : EntityValue

    @Serializable
    @SerialName("Entity.SPD")
    data object SPD : EntityValue

    @Serializable
    @SerialName("Entity.HP")
    data object HP : EntityValue

    @Serializable
    @SerialName("Entity.SP")
    data object SP : EntityValue

    @Serializable
    @SerialName("Entity.AP")
    data object AP : EntityValue

    @Serializable
    @SerialName("Entity.HPRate")
    data object HPRate : EntityValue

    @Serializable
    @SerialName("Entity.SPRate")
    data object SPRate : EntityValue

    @Serializable
    @SerialName("Entity.APRate")
    data object APRate : EntityValue

    @Serializable
    @SerialName("Entity.MAXHP")
    data object MAXHP : EntityValue

    @Serializable
    @SerialName("Entity.MAXSP")
    data object MAXSP : EntityValue

    @Serializable
    @SerialName("Entity.MAXAP")
    data object MAXAP : EntityValue

    @Serializable
    @SerialName("Entity.BaseATK")
    data object BaseATK : EntityValue

    @Serializable
    @SerialName("Entity.BaseDEF")
    data object BaseDEF : EntityValue

    @Serializable
    @SerialName("Entity.BaseSPD")
    data object BaseSPD : EntityValue

    @Serializable
    @SerialName("Entity.BaseHP")
    data object BaseHP : EntityValue

    @Serializable
    @SerialName("Entity.BaseSP")
    data object BaseSP : EntityValue

    @Serializable
    @SerialName("Entity.BaseAP")
    data object BaseAP : EntityValue

    @Serializable
    @SerialName("Entity.CriticalRate")
    data object CriticalRate : EntityValue

    @Serializable
    @SerialName("Entity.CriticalDMG")
    data object CriticalDMG : EntityValue

    /**
     * The turns left of the effects with a specified name.
     *
     * If the effect is stackable, the return value will be the sum of all stacked effects.
     *
     * Not applicable for the items (always return 0)
     */
    @Serializable
    @SerialName("Entity.TurnsLeftOf")
    data class TurnsLeftOf(val effectName: String) : EntityValue

    /**
     * Returns the total number of turns across all effects.
     *
     * Not applicable for the items (always return 0)
     */
    @Serializable
    @SerialName("Entity.TurnsLeftOfAllEffects")
    data object TurnsLeftOfAllEffects : EntityValue

    /**
     * The remaining value of the shields with the specified key.
     *
     * Not applicable for the items (always return 0)
     */
    @Serializable
    @SerialName("Entity.ShieldValueOf")
    data class ShieldValueOf(val key: String) : EntityValue

    /**
     * The remaining value of all the shields.
     *
     * Not applicable for the items (always return 0)
     */
    @Serializable
    @SerialName("Entity.SumOfShieldValue")
    data object SumOfShieldValue : EntityValue
}