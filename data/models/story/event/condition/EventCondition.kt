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
package sokeriaaa.return0.shared.data.models.story.event.condition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.common.Comparator
import sokeriaaa.return0.shared.data.models.story.event.value.EventValue
import sokeriaaa.return0.shared.data.models.title.Title

/**
 * Condition components for events.
 */
@Serializable
sealed interface EventCondition {

    @Serializable
    @SerialName("True")
    data object True : EventCondition

    @Serializable
    @SerialName("False")
    data object False : EventCondition

    /**
     * Read the saved switch.
     */
    @Serializable
    @SerialName("SavedSwitch")
    data class SavedSwitch(val key: String, val default: EventCondition = False) : EventCondition

    /**
     * Compare two values.
     */
    @Serializable
    @SerialName("Compare")
    data class Compare(
        val value1: EventValue,
        val comparator: Comparator,
        val value2: EventValue,
    ) : EventCondition

    /**
     * Player title.
     */
    @Serializable
    @SerialName("PlayerTitle")
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
    @SerialName("QuestCompleted")
    data class QuestCompleted(
        val key: String,
    ) : EventCondition

}