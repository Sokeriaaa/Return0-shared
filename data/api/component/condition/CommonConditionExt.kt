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
package sokeriaaa.return0.shared.data.api.component.condition

import sokeriaaa.return0.shared.data.api.component.value.Value
import sokeriaaa.return0.shared.data.models.component.conditions.CommonCondition
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Operators for common math calculation.
 */
infix fun Condition.and(condition2: Condition): Condition = CommonCondition.And(this, condition2)
infix fun Condition.or(condition2: Condition): Condition = CommonCondition.Or(this, condition2)
operator fun Condition.not() = CommonCondition.Not(this)

infix fun Value.gt(value2: Value): Condition =
    CommonCondition.Compare(this, value2, isIncludeEquals = false)

infix fun Value.gt(value2: Int): Condition =
    CommonCondition.Compare(this, Value(value2), isIncludeEquals = false)

infix fun Value.gt(value2: Float): Condition =
    CommonCondition.Compare(this, Value(value2), isIncludeEquals = false)

infix fun Value.lt(value2: Value): Condition =
    CommonCondition.Compare(value2, this, isIncludeEquals = false)

infix fun Value.lt(value2: Int): Condition =
    CommonCondition.Compare(Value(value2), this, isIncludeEquals = false)

infix fun Value.lt(value2: Float): Condition =
    CommonCondition.Compare(Value(value2), this, isIncludeEquals = false)

infix fun Value.gtEq(value2: Value): Condition =
    CommonCondition.Compare(this, value2, isIncludeEquals = true)

infix fun Value.gtEq(value2: Int): Condition =
    CommonCondition.Compare(this, Value(value2), isIncludeEquals = true)

infix fun Value.gtEq(value2: Float): Condition =
    CommonCondition.Compare(this, Value(value2), isIncludeEquals = true)

infix fun Value.ltEq(value2: Value): Condition =
    CommonCondition.Compare(value2, this, isIncludeEquals = true)

infix fun Value.ltEq(value2: Int): Condition =
    CommonCondition.Compare(Value(value2), this, isIncludeEquals = true)

infix fun Value.ltEq(value2: Float): Condition =
    CommonCondition.Compare(Value(value2), this, isIncludeEquals = true)