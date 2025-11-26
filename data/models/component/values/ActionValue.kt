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

import kotlinx.serialization.Serializable

/**
 * Value types for actions, for the action itself.
 */
@Serializable
sealed interface ActionValue : Value {

    @Serializable
    sealed interface Skills : ActionValue {
        /**
         * @see sokeriaaa.return0.models.action.function.Skill.power
         */
        @Serializable
        data object Power : Skills

        /**
         * @see sokeriaaa.return0.models.action.function.Skill.basePower
         */
        @Serializable
        data object BasePower : Skills
    }

    @Serializable
    sealed interface Effects : ActionValue {
        /**
         * @see sokeriaaa.return0.models.action.effect.Effect.turnsLeft
         */
        @Serializable
        data object TurnsLeft : Effects
    }

    /**
     * @see sokeriaaa.return0.models.action.Action.tier
     */
    @Serializable
    data object Tier : ActionValue

    /**
     * @see sokeriaaa.return0.models.action.Action.timesUsed
     */
    @Serializable
    data object TimesUsed : ActionValue

    /**
     * @see sokeriaaa.return0.models.action.Action.timesRepeated
     */
    @Serializable
    data object TimesRepeated : ActionValue

}