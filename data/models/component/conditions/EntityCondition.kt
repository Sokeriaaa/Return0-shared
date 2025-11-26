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
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.entity.category.Category

@Serializable
sealed interface EntityCondition : Condition {

    @Serializable
    sealed interface Categories : EntityCondition {
        /**
         * The target has the [category].
         */
        @Serializable
        data class Has(val category: Category) : Categories

        /**
         * The target has the one or more of categories in [categories].
         */
        @Serializable
        data class HasOneOf(val categories: List<Category>) : Categories
    }

    @Serializable
    sealed interface Effects : EntityCondition {
        /**
         * The target has the effect with [effectName].
         */
        @Serializable
        data class Has(val effectName: String) : Effects

        /**
         * The target has any effect.
         */
        @Serializable
        data object HasAny : Effects
    }

    @Serializable
    sealed interface Status : EntityCondition {
        /**
         * The target has less HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXHP.
         */
        @Serializable
        data class HPLessThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has more HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXHP.
         */
        @Serializable
        data class HPMoreThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has less SP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXSP.
         */
        @Serializable
        data class SPLessThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has more HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXSP.
         */
        @Serializable
        data class SPMoreThan(val rate: Value, val isIncludeEquals: Boolean) : Status
    }

}