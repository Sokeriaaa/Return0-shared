/**
 * Copyright (C) 2026 Sokeriaaa
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
package sokeriaaa.return0.shared.common.helpers

import kotlin.math.pow

fun Float.toPrecision(precision: Int): String {
    if (precision <= 0) {
        return this.toInt().toString()
    }
    val integerDigits = this.toInt()
    val digits = 10f.pow(precision).toInt()
    val floatDigits = (this * digits).toInt() % digits
    return "$integerDigits.${floatDigits.toString().padStart(precision, padChar = '0')}"
}
