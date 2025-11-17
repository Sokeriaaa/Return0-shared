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
package sokeriaaa.return0.shared.common.helpers

import kotlin.random.Random

/**
 * Returns `true` with chance of [success]/[base], otherwise `false`.
 */
fun chance(success: Int, base: Int = 1): Boolean = Random.nextInt(base) < success

/**
 * Returns `true` with chance of [success]/[base], otherwise `false`.
 */
fun chance(success: Long, base: Long = 1L): Boolean = Random.nextLong(base) < success

/**
 * Returns `true` with chance of [success]/[base], otherwise `false`.
 */
fun chance(success: Double, base: Double = 1.0): Boolean = Random.nextDouble() * base < success

/**
 * Returns `true` with chance of [success]/[base], otherwise `false`.
 */
fun chance(success: Float, base: Float = 1F): Boolean = Random.nextFloat() * base < success

/**
 * Run [ifSuccess] with chance of [success]/[base].
 */
inline fun withChance(success: Int, base: Int = 1, ifSuccess: () -> Unit) {
    if (chance(success = success, base = base)) {
        ifSuccess()
    }
}

/**
 * Run [ifSuccess] with chance of [success]/[base].
 */
inline fun withChance(success: Long, base: Long = 1L, ifSuccess: () -> Unit) {
    if (chance(success = success, base = base)) {
        ifSuccess()
    }
}

/**
 * Run [ifSuccess] with chance of [success]/[base].
 */
inline fun withChance(success: Double, base: Double = 1.0, ifSuccess: () -> Unit) {
    if (chance(success = success, base = base)) {
        ifSuccess()
    }
}