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

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object TimeHelper {

    const val ONE_HOUR = 1000L * 60L * 60L
    const val ONE_DAY = ONE_HOUR * 24L
    const val ONE_WEEK = ONE_DAY * 7L

    @OptIn(ExperimentalTime::class)
    fun currentTimeMillis(): Long = Clock.System.now().toEpochMilliseconds()

    /**
     * Get the time millis at 0:00 UTC the next day.
     */
    fun nextDay(now: Long = currentTimeMillis()): Long =
        ((now / ONE_DAY) + 1) * ONE_DAY

    /**
     * Get the time millis at 0:00 UTC next Sunday.
     * (January 1, 1970 was a Thursday.)
     */
    fun nextSunday(now: Long = currentTimeMillis()): Long {
        val daysSinceEpoch = now / ONE_DAY
        val dayOfWeek = (daysSinceEpoch + 4) % 7
        val daysUntilSunday = (7 - dayOfWeek) % 7
        return (daysSinceEpoch + daysUntilSunday) * ONE_DAY
    }

    fun millisToString(millis: Long): String {
        val instant = Instant.fromEpochMilliseconds(millis)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return localDateTime.toString()
    }

}