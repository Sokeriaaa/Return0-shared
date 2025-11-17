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

import sokeriaaa.return0.models.entity.category.Category
import sokeriaaa.return0.shared.data.models.component.values.Value

sealed interface EntityCondition : Condition {

    sealed interface Categories : EntityCondition {
        /**
         * The target has the [category].
         */
        data class Has(val category: Category) : Categories

        /**
         * The target has the one or more of categories in [categories].
         */
        data class HasOneOf(val categories: List<Category>) : Categories
    }

    sealed interface Effects : EntityCondition {
        /**
         * The target has the effect with [effectName].
         */
        data class Has(val effectName: String) : Effects

        /**
         * The target has any effect.
         */
        data object HasAny : Effects
    }

    sealed interface Status : EntityCondition {
        /**
         * The target has less HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXHP.
         */
        data class HPLessThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has more HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXHP.
         */
        data class HPMoreThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has less SP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXSP.
         */
        data class SPLessThan(val rate: Value, val isIncludeEquals: Boolean) : Status

        /**
         * The target has more HP (or equals to when [isIncludeEquals] == true)
         * than the rate of MAXSP.
         */
        data class SPMoreThan(val rate: Value, val isIncludeEquals: Boolean) : Status
    }

}