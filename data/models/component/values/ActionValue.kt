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
 * Value types for actions, for the action itself.
 */
@Serializable
@SerialName("Action")
sealed interface ActionValue : Value {

    @Serializable
    @SerialName("Action.Skills")
    sealed interface Skills : ActionValue {
        /**
         * @see sokeriaaa.return0.models.action.function.Skill.power
         */
        @Serializable
        @SerialName("Action.Skills.Power")
        data object Power : Skills

        /**
         * @see sokeriaaa.return0.models.action.function.Skill.basePower
         */
        @Serializable
        @SerialName("Action.Skills.BasePower")
        data object BasePower : Skills
    }

    @Serializable
    @SerialName("Action.Effects")
    sealed interface Effects : ActionValue {
        /**
         * @see sokeriaaa.return0.models.action.effect.Effect.turnsLeft
         */
        @Serializable
        @SerialName("Action.Effects.TurnsLeft")
        data object TurnsLeft : Effects
    }

    /**
     * @see sokeriaaa.return0.models.action.Action.tier
     */
    @Serializable
    @SerialName("Action.Tier")
    data object Tier : ActionValue

    /**
     * @see sokeriaaa.return0.models.action.Action.timesUsed
     */
    @Serializable
    @SerialName("Action.TimesUsed")
    data object TimesUsed : ActionValue

    /**
     * @see sokeriaaa.return0.models.action.Action.timesRepeated
     */
    @Serializable
    @SerialName("Action.TimesRepeated")
    data object TimesRepeated : ActionValue

}