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
 * Data of the target. Can be wrapped with forUser to apply for user.
 */
@Serializable
sealed interface EntityValue : Value {
    @Serializable
    data object ATK : EntityValue

    @Serializable
    data object DEF : EntityValue

    @Serializable
    data object SPD : EntityValue

    @Serializable
    data object HP : EntityValue

    @Serializable
    data object SP : EntityValue

    @Serializable
    data object AP : EntityValue

    @Serializable
    data object MAXHP : EntityValue

    @Serializable
    data object MAXSP : EntityValue

    @Serializable
    data object MAXAP : EntityValue

    @Serializable
    data object BaseATK : EntityValue

    @Serializable
    data object BaseDEF : EntityValue

    @Serializable
    data object BaseSPD : EntityValue

    @Serializable
    data object BaseHP : EntityValue

    @Serializable
    data object BaseSP : EntityValue

    @Serializable
    data object BaseAP : EntityValue

    @Serializable
    data object CriticalRate : EntityValue

    @Serializable
    data object CriticalDMG : EntityValue

    /**
     * The turns left of the effects with a specified name.
     *
     * If the effect is stackable, the return value will be the sum of all stacked effects.
     */
    @Serializable
    data class TurnsLeftOf(val effectName: String) : EntityValue

    /**
     * Returns the total number of turns across all effects.
     */
    @Serializable
    data object TurnsLeftOfAllEffects : EntityValue
}