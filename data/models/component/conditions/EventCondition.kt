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
import sokeriaaa.return0.shared.data.models.component.values.TimeValue
import sokeriaaa.return0.shared.data.models.title.Title

sealed interface EventCondition : Condition.Event {
    /**
     * Read the saved switch.
     */
    @Serializable
    @SerialName("Event.SavedSwitch")
    data class SavedSwitch(
        val key: String,
        val default: Condition.Event = CommonCondition.False,
    ) : EventCondition


    /**
     * Player title.
     */
    @Serializable
    @SerialName("Event.PlayerTitle")
    data class PlayerTitle(
        val comparator: Comparator,
        val title: Title,
    ) : EventCondition

    /**
     * Whether the quest with specified key is completed.
     *
     * Note that after a completed quest is expired, this will return `false`.
     */
    @Serializable
    @SerialName("Event.QuestCompleted")
    data class QuestCompleted(
        val key: String,
    ) : EventCondition

    /**
     * Compare the current time with the [time] value.
     */
    @Serializable
    @SerialName("Event.CompareTime")
    data class CompareTime(
        val comparator: Comparator,
        val time: TimeValue,
    ) : EventCondition
}