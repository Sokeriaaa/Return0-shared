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
import sokeriaaa.return0.shared.data.models.component.common.Comparator
import sokeriaaa.return0.shared.data.models.component.conditions.CommonCondition
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Operators for common math calculation.
 */
infix fun Condition.and(condition2: Condition) = CommonCondition.And(this, condition2)
infix fun Condition.or(condition2: Condition) = CommonCondition.Or(this, condition2)
operator fun Condition.not() = CommonCondition.Not(this)

infix fun Value.gt(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.GT, value2)

infix fun Value.gt(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.GT, Value(value2))

infix fun Value.gt(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.GT, Value(value2))

infix fun Value.lt(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.LT, value2)

infix fun Value.lt(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.LT, Value(value2))

infix fun Value.lt(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.LT, Value(value2))

infix fun Value.gtEq(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.GTEQ, value2)

infix fun Value.gtEq(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.GTEQ, Value(value2))

infix fun Value.gtEq(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.GTEQ, Value(value2))

infix fun Value.ltEq(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.LTEQ, value2)

infix fun Value.ltEq(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.LTEQ, Value(value2))

infix fun Value.ltEq(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.LTEQ, Value(value2))

infix fun Value.eq(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.EQ, value2)

infix fun Value.eq(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.EQ, Value(value2))

infix fun Value.eq(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.EQ, Value(value2))

infix fun Value.neq(value2: Value) =
    CommonCondition.CompareValues(this, Comparator.NEQ, value2)

infix fun Value.neq(value2: Int) =
    CommonCondition.CompareValues(this, Comparator.NEQ, Value(value2))

infix fun Value.neq(value2: Float) =
    CommonCondition.CompareValues(this, Comparator.NEQ, Value(value2))