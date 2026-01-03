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
package sokeriaaa.return0.shared.data.models.component.values

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Calculate the time in millis mainly for event conditions.
 */
@Serializable
sealed interface TimeValue : Value.Time {

    /**
     * System.currentTimeMillis() / Clock.System.now().toEpochMilliseconds()
     */
    @Serializable
    @SerialName("Now")
    data object Now : TimeValue

    /**
     * Millis after now.
     */
    @Serializable
    @SerialName("After")
    data class After(val millis: Long) : TimeValue

    /**
     * 0:00 UTC of the next day. Can apply offset.
     */
    @Serializable
    @SerialName("NextDay")
    data class NextDay(val offsetMillis: Long = 0L) : TimeValue

    /**
     * 0:00 UTC of the next **Sunday**. Can apply offset.
     */
    @Serializable
    @SerialName("NextWeek")
    data class NextWeek(val offsetMillis: Long = 0L) : TimeValue

    /**
     * The saved time with specified key.
     */
    @Serializable
    @SerialName("Saved")
    data class Saved(val key: String) : TimeValue

    /**
     * Directly input the time in millis. Mainly for testing proposes or default values.
     */
    @Serializable
    @SerialName("Custom")
    data class Custom(val timeInMillis: Long) : TimeValue

}