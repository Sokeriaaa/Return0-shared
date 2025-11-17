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

/**
 * Data of the target. Can be wrapped with forUser to apply for user.
 */
sealed interface EntityValue : Value {
    data object ATK : EntityValue
    data object DEF : EntityValue
    data object SPD : EntityValue
    data object HP : EntityValue
    data object SP : EntityValue
    data object AP : EntityValue
    data object MAXHP : EntityValue
    data object MAXSP : EntityValue
    data object MAXAP : EntityValue
    data object BaseATK : EntityValue
    data object BaseDEF : EntityValue
    data object BaseSPD : EntityValue
    data object BaseHP : EntityValue
    data object BaseSP : EntityValue
    data object BaseAP : EntityValue
    data object CriticalRate : EntityValue
    data object CriticalDMG : EntityValue

    /**
     * The turns left of the effects with a specified name.
     *
     * If the effect is stackable, the return value will be the sum of all stacked effects.
     */
    data class TurnsLeftOf(val effectName: String) : EntityValue

    /**
     * Returns the total number of turns across all effects.
     */
    data object TurnsLeftOfAllEffects : EntityValue
}