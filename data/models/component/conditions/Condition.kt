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
package sokeriaaa.return0.shared.data.models.component.conditions

import kotlinx.serialization.Serializable

/**
 * A condition class being calculated during the combat.
 */
@Serializable
sealed interface Condition {

    /**
     * This condition is applicable for the combats.
     */
    @Serializable
    sealed interface Combat : Condition


    /**
     * This condition is applicable for the events.
     */
    @Serializable
    sealed interface Event : Condition

}