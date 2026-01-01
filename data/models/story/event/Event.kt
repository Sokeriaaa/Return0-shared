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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.currency.CurrencyType

/**
 * Story events.
 */
@Serializable
sealed interface Event {

    /**
     * An empty event for placeholder.
     */
    @Serializable
    @SerialName("Empty")
    data object Empty : Event

    /**
     * Execute a list of events one by one.
     */
    @Serializable
    @SerialName("Sequence")
    data class Sequence(val events: List<Event>) : Event {
        constructor(vararg events: Event) : this(listOf(*events))
    }

    /**
     * Conditioned fork.
     */
    @Serializable
    @SerialName("Conditioned")
    data class Conditioned(
        val condition: Condition.Event,
        val ifTrue: Event = Empty,
        val ifFalse: Event = Empty,
    ) : Event

    /**
     * Conversation.
     */
    @Serializable
    @SerialName("Text")
    sealed class Text : Event {
        abstract val messageRes: String

        @Serializable
        @SerialName("Text.Narrator")
        data class Narrator(override val messageRes: String) : Text()

        @Serializable
        @SerialName("Text.User")
        data class User(override val messageRes: String) : Text()

        @Serializable
        @SerialName("Text.NPC")
        data class NPC(val nameRes: String, override val messageRes: String) : Text()
    }

    /**
     * Display a tips
     */
    @Serializable
    @SerialName("Tips")
    data class Tips(
        val tipsRes: String,
    ) : Event

    /**
     * Ask the user to make a decision.
     */
    @Serializable
    @SerialName("Choice")
    data class Choice(
        val items: List<Pair<String, Event>>,
    ) : Event {
        constructor(vararg items: Pair<String, Event>) : this(listOf(*items))
    }

    /**
     * Let the user select every of the [items] exactly once (No fixed order required).
     *
     * After all items is selected, execute [onFinished].
     */
    @Serializable
    @SerialName("ChoiceOneByOne")
    data class ChoiceOneByOne(
        val items: List<Pair<String, Event>>,
        val onFinished: Event = Empty,
    ) : Event {
        constructor(vararg items: Pair<String, Event>, onFinished: Event = Empty) : this(listOf(*items), onFinished)
    }

    /**
     * Start a combat with certain config.
     */
    @Serializable
    @SerialName("Combat")
    data class Combat(
        val config: Config,
        val success: Event = Empty,
        val failure: Event = Failed,
    ) : Event {
        /**
         * Combat config.
         *
         * @param enemies Enemies the player will fight with in this combat. Entity name to level.
         * @param difficulty Difficulty modifier for this combat. Affects the EXP and token
         *                   rewards after winning the combat.
         * @param additionalParties Additional parties in this combat. Will replace user's team
         *                          from index #3 to #0. If the entity with a higher level is
         *                          already exists in the user's own team, use the user's entity
         *                          instead.
         * @param useOnlyAdditional Use only the [additionalParties] without any user's own
         *                          entities. The [additionalParties] must not be empty when this
         *                          flag is `true`, or the combat will fail immediately when it's
         *                          started.
         * @param statusOverride Overrides the status of parties before combat starts. The key is
         *                       entity name.
         *                       When the map itself is null, it means this combat will use the
         *                       current status of entities, and the HP/SP will be saved after the
         *                       combat is finished and used for the next combat. It's a normal
         *                       combat.
         *                       if the map is not null, it means this is a special combat. Status
         *                       will not be saved to the database after the combat ended. Entity
         *                       status will be overridden temporary during this combat. If a key
         *                       for the entity doesn't exists, use the default value of
         *                       [EntityOverride].
         */
        @Serializable
        data class Config(
            val enemies: List<Pair<String, Value.Event>>,
            val difficulty: Float = 1F,
            val additionalParties: List<Pair<String, Value.Event>> = emptyList(),
            val useOnlyAdditional: Boolean = false,
            val statusOverride: Map<String, StatusOverride>? = null,
        ) {
            /**
             * Override the entity status for this combat temporary.
             *
             * @param hp Overrode HP. Default is null, presents full HP.
             * @param sp Overrode SP. Default is null, presents full SP.
             * @param level Overrode level. Default is null, presents current level.
             */
            @Serializable
            data class StatusOverride(
                val hp: Value.Event? = null,
                val sp: Value.Event? = null,
                val level: Value.Event? = null,
            )
        }
    }

    /**
     * Teleport the user to a specified location.
     */
    @Serializable
    @SerialName("MoveUserTo")
    data class MoveUserTo(
        val lineNumber: Value.Event,
    ) : Event

    /**
     * Teleport the user to a specified location.
     */
    @Serializable
    @SerialName("TeleportUserTo")
    data class TeleportUserTo(
        val map: String,
        val lineNumber: Value.Event,
    ) : Event

    /**
     * Teleport this event to a specified line number.
     * Has no effect when this event don't have coordinates.
     */
    @Serializable
    @SerialName("TeleportThisEventTo")
    data class TeleportThisEventTo(
        val lineNumber: Value.Event,
    ) : Event

    /**
     * Player inventory change.
     */
    @Serializable
    @SerialName("InventoryChange")
    data class InventoryChange(
        val inventoryKey: String,
        val change: Value.Event,
    ) : Event

    /**
     * Player inventory change.
     */
    @Serializable
    @SerialName("CurrencyChange")
    data class CurrencyChange(
        val currency: CurrencyType,
        val change: Value.Event,
    ) : Event

    /**
     * Claim a quest.
     */
    @Serializable
    @SerialName("ClaimQuest")
    data class ClaimQuest(
        val key: String
    ) : Event

    /**
     * Complete a quest.
     */
    @Serializable
    @SerialName("CompleteQuest")
    data class CompleteQuest(
        val key: String
    ) : Event

    /**
     * Save a boolean value to current save.
     */
    @Serializable
    @SerialName("SaveSwitch")
    data class SaveSwitch(val key: String, val switch: Condition.Event) : Event

    /**
     * Save an int value to current save.
     */
    @Serializable
    @SerialName("SaveVariable")
    data class SaveVariable(val key: String, val variable: Value.Event) : Event

    /**
     * Obtain a new entity with the initial status.
     */
    @Serializable
    @SerialName("ObtainEntity")
    data class ObtainEntity(
        val entityName: String,
        val level: Int = 1,
        val exp: Int = 0,
        val currentHP: Int? = null,
        val currentSP: Int? = null,
        val pluginID: Long? = null,
    ) : Event

    /**
     * Show the map again after being hidden by [HideMap].
     */
    @Serializable
    @SerialName("ShowMap")
    data object ShowMap : Event

    /**
     * Hide the map display during this event. The map will display automatically after the parent
     *  event is finished.
     */
    @Serializable
    @SerialName("HideMap")
    data object HideMap : Event

    /**
     * One shot effect to shake the map.
     */
    @Serializable
    @SerialName("ShakeMap")
    data object ShakeMap : Event

    /**
     * Fully recover all entities in current team.
     */
    @Serializable
    @SerialName("RecoverAll")
    data object RecoverAll : Event

    /**
     * Mainly used by items.
     *
     * Notify the user to choose an entity for applying the extras. A special [ActionExtraContext]
     * will be used here, which currently only allows the basic HP/SP change to the target, other
     * extras which affects the states that don't save to the database will take no effect.
     *
     * [onCanceled] will be executed if [extra] is null or user canceled the selection.
     */
    @Serializable
    @SerialName("ExecuteExtra")
    data class ExecuteExtra(
        val extra: Extra?,
        val onCanceled: Event = RefuseToUse(),
    ) : Event

    /**
     * Ask the user to save current game progress.
     */
    @Serializable
    @SerialName("RequestSave")
    data object RequestSave : Event

    /**
     * Fail this game. Teleport the user to last checkpoint.
     */
    @Serializable
    @SerialName("Failed")
    data object Failed : Event

    /**
     * A special event that requests the user to input "return 0;" in a text field.
     */
    @Serializable
    @SerialName("TypeReturn0")
    data object TypeReturn0 : Event

    /**
     * Can only used by items. Throws an exception to interrupt the usage of item.
     */
    @Serializable
    @SerialName("RefuseToUse")
    data class RefuseToUse(val reasonRes: String? = null) : Event
}