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
package sokeriaaa.return0.shared.data.models.entity

import sokeriaaa.return0.shared.data.models.action.function.FunctionData
import sokeriaaa.return0.shared.data.models.action.function.FunctionTarget
import sokeriaaa.return0.shared.data.models.entity.category.Category

/**
 * Entity data class.
 *
 * @param name Name of the Entity.
 * @param category Primary category of this Entity.
 * @param category2 Secondary category of this Entity. (If have)
 * @param functions Functions of this entity.
 * @param attackModifier Modifier for the base attack action.
 */
data class EntityData(
    val name: String,
    val category: Category,
    val category2: Category? = null,
    val baseATK: Int,
    val baseDEF: Int,
    val baseSPD: Int,
    val baseHP: Int,
    val baseSP: Int,
    val baseAP: Int,
    val functions: List<FunctionData>,
    val attackModifier: GeneralAttackModifier? = null
) {
    /**
     * The modifier for the general attack action for entities. It's possible to alter the power
     *  even attack times for entity's attack action.
     */
    data class GeneralAttackModifier(
        val power: Int? = null,
        val attackTimes: Int? = null,
        val target: FunctionTarget? = null,
    )
}