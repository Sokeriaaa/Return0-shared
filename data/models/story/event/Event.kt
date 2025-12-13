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
package sokeriaaa.return0.shared.data.models.story.event

import sokeriaaa.return0.shared.data.models.combat.ArenaConfig
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType
import sokeriaaa.return0.shared.data.models.story.event.condition.EventCondition
import sokeriaaa.return0.shared.data.models.story.event.value.EventValue

/**
 * Story events.
 */
sealed interface Event {

    /**
     * An empty event for placeholder.
     */
    data object Empty : Event

    /**
     * Execute a list of events one by one.
     */
    data class Sequence(val events: List<Event>) : Event

    /**
     * Conditioned fork.
     */
    data class Conditioned(
        val condition: EventCondition,
        val ifTrue: Event = Empty,
        val ifFalse: Event = Empty,
    ) : Event

    /**
     * Conversation.
     */
    sealed class Text : Event {
        abstract val messageRes: String

        data class Narrator(override val messageRes: String) : Text()
        data class User(override val messageRes: String) : Text()
        data class NPC(val nameRes: String, override val messageRes: String) : Text()
    }

    /**
     * Ask the user to make a decision.
     */
    data class Choice(
        val items: List<Pair<String, Event>>,
    ) : Event

    /**
     * Start a combat with certain config.
     */
    data class Combat(
        val config: ArenaConfig,
        val success: Event = Empty,
        val failure: Event = Failed,
    ) : Event

    /**
     * Teleport the user to a specified location.
     */
    data class MoveUserTo(
        val map: String,
        val lineNumber: EventValue,
    ) : Event

    /**
     * Teleport this event to a specified line number.
     * Has no effect when this event don't have coordinates.
     */
    data class MoveThisEventTo(
        val lineNumber: EventValue,
    ) : Event

    /**
     * Player inventory change.
     */
    data class InventoryChange(
        val inventoryKey: String,
        val change: EventValue,
    ) : Event

    /**
     * Player inventory change.
     */
    data class CurrencyChange(
        val currency: CurrencyType,
        val change: EventValue,
    ) : Event

    /**
     * Claim a quest.
     */
    data class ClaimQuest(
        val key: String
    ) : Event

    /**
     * Complete a quest.
     */
    data class CompleteQuest(
        val key: String
    ) : Event

    /**
     * Save a boolean value to current save.
     */
    data class SaveSwitch(val key: String, val switch: EventCondition) : Event

    /**
     * Save an int value to current save.
     */
    data class SaveVariable(val key: String, val variable: EventValue) : Event

    /**
     * Fully recover all entities in current team.
     */
    data object RecoverAll : Event

    /**
     * Ask the user to save current game progress.
     */
    data object RequestSave : Event

    /**
     * Fail this game. Teleport the user to last checkpoint.
     */
    data object Failed : Event

}