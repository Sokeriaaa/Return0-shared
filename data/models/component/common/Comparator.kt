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
package sokeriaaa.return0.shared.data.models.component.common

import kotlinx.serialization.Serializable

/**
 * Common comparator for components.
 */
@Serializable
enum class Comparator {
    /**
     * Greater
     */
    GT,

    /**
     * Greater or equals
     */
    GTEQ,

    /**
     * Less
     */
    LT,

    /**
     * Less or equals
     */
    LTEQ,

    /**
     * Equals
     */
    EQ,

    /**
     * Not equals
     */
    NEQ,
    ;

    fun compare(value1: Float, value2: Float): Boolean {
        return when (this) {
            GT -> value1 > value2
            GTEQ -> value1 >= value2
            LT -> value1 < value2
            LTEQ -> value1 <= value2
            EQ -> value1 == value2
            NEQ -> value1 != value2
        }
    }

    fun compare(value1: Int, value2: Int): Boolean {
        return when (this) {
            GT -> value1 > value2
            GTEQ -> value1 >= value2
            LT -> value1 < value2
            LTEQ -> value1 <= value2
            EQ -> value1 == value2
            NEQ -> value1 != value2
        }
    }

    companion object {
        // Alias
        val GREATER = GT
        val GREATER_EQUAL = GTEQ
        val LESS = LT
        val LESS_EQUAL = LTEQ
        val EQUALS = EQ
        val NOT_EQUALS = NEQ
    }
}