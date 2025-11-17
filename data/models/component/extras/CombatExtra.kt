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
package sokeriaaa.return0.shared.data.models.component.extras

import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Extras for the combat.
 */
sealed interface CombatExtra : Extra {
    data class HPChange(val hpChange: Value) : CombatExtra
    data class SPChange(val spChange: Value) : CombatExtra
    data class APChange(val apChange: Value) : CombatExtra
    data class AttachEffect(val name: String, val tier: Value, val turns: Value) : CombatExtra
    data class RemoveEffect(val name: String) : CombatExtra
    data object RemoveAllEffect : CombatExtra
}