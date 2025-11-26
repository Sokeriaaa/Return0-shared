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
package sokeriaaa.return0.shared.data.models.entity.category

import kotlinx.serialization.Serializable

/**
 * Effectiveness for damaging entities by an action.
 */
@Serializable
data class CategoryEffectiveness(
    val attack: List<Rate>,
    val defend: List<Rate>,
) {
    /**
     * The rate type of category effectiveness.
     *
     * @param category2 The category to attack/defend
     * @param rateType Final damage is offset by [rateType] * 10%.
     *  This value is between -2 and 2.
     */
    @Serializable
    data class Rate(
        val category2: Category,
        val rateType: Int,
    )
}
