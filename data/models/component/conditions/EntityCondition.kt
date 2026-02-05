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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.common.Comparator
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.entity.category.Category

@Serializable
@SerialName("Entity")
sealed interface EntityCondition : Condition.Combat {

    @Serializable
    @SerialName("Entity.Categories")
    sealed interface Categories : EntityCondition, Condition.Item {
        /**
         * The target has the [category].
         */
        @Serializable
        @SerialName("Entity.Categories.Has")
        data class Has(val category: Category) : Categories

        /**
         * The target has the one or more of categories in [categories].
         */
        @Serializable
        @SerialName("Entity.Categories.HasOneOf")
        data class HasOneOf(val categories: List<Category>) : Categories {
            constructor(vararg categories: Category) : this(categories.toList())
        }
    }

    @Serializable
    @SerialName("Entity.Effects")
    sealed interface Effects : EntityCondition {
        /**
         * The target has the effect with [effectName].
         */
        @Serializable
        @SerialName("Entity.Effects.Has")
        data class Has(val effectName: String) : Effects

        /**
         * The target has any effect.
         */
        @Serializable
        @SerialName("Entity.Effects.HasAny")
        data class HasAny(val buff: Boolean = false, val debuff: Boolean = false) : Effects
    }

    @Serializable
    @SerialName("Entity.Shields")
    sealed interface Shields : EntityCondition {
        /**
         * The target has the shield with the [key].
         */
        @Serializable
        @SerialName("Entity.Shields.Has")
        data class Has(val key: String) : Shields

        /**
         * The target has any shields.
         */
        @Serializable
        @SerialName("Entity.Shields.HasAny")
        data object HasAny : Shields
    }

    @Serializable
    @SerialName("Entity.Status")
    sealed interface Status : EntityCondition, Condition.Item {
        /**
         * Compare the current HP of the rate of MAXHP with the specified rate.
         */
        @Serializable
        @SerialName("Entity.Status.HPRate")
        data class HPRate(val comparator: Comparator, val rate: Value.Combat) : Status

        /**
         * Compare the current SP of the rate of MAXSP with the specified rate.
         */
        @Serializable
        @SerialName("Entity.Status.SPRate")
        data class SPRate(val comparator: Comparator, val rate: Value.Combat) : Status

        /**
         * Whether this entity is failed (hp == 0)
         */
        @Serializable
        @SerialName("Entity.Status.IsFailed")
        data object IsFailed : Status
    }

}