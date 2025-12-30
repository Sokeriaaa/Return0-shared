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

import kotlinx.serialization.Serializable

/**
 * Extras being executed after a successful action.
 */
@Serializable
sealed interface Extra {
    /**
     * This extra is applicable for the combat.
     */
    @Serializable
    sealed interface Combat : Extra

    /**
     * This extra is applicable for the item usages outside a combat.
     *
     * During a combat, the item will be executed as an action and [Combat] is used.
     */
    @Serializable
    sealed interface Item : Extra
}