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
import sokeriaaa.return0.shared.data.models.component.extras.Extra

/**
 * Data class for effect.
 *
 * @param name Name of this effect.
 * @param abbr The abbreviation of this effect. Can be 2~4 characters.
 * @param isDebuff Whether this effect is a debuff or not.
 * @param isStackable Whether this effect is stackable or not. If not stackable,
 *  when an effect with same name but different user is about to add, firstly keep
 *  the one with a higher tier, then the one with a longer turns left.
 * @param isRemovable Whether this effect can be removed by a function that removes effects.
 * @param isFreeze Current entity will be frozen and skip the action during the effect time.
 * @param modifiers Modifiers of this effect.
 * @param extra Extra is executed everytime after current entity taking an action.
 */
@Serializable
data class EffectData(
    val name: String,
    val abbr: String,
    val isDebuff: Boolean,
    val isStackable: Boolean = false,
    val isRemovable: Boolean = true,
    val isFreeze: Boolean = false,
    val modifiers: List<EffectModifier> = emptyList(),
    val extra: Extra? = null,
)