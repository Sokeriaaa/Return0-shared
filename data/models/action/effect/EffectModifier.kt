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
package sokeriaaa.return0.shared.data.models.action.effect

import kotlinx.serialization.Serializable

/**
 * Modifier for an effect.
 *
 * @param offset The base offset rate for tier 1.
 * @param tierBonus The bonus of each tier. final rate is offset + tierBonus * (tier - 1).
 */
@Serializable
data class EffectModifier(
    val type: Types,
    val offset: Float,
    val tierBonus: Float,
) {
    @Serializable
    enum class Types {
        ATK,
        DEF,
        SPD,
        MAXHP,
        MAXSP,
        MAXAP,
        CRIT_RATE,
        CRIT_DMG,
        TARGET_RATE,
        HIDE_RATE,
    }
}