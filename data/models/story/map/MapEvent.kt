package sokeriaaa.return0.shared.data.models.story.map

import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.story.event.Event
import sokeriaaa.return0.shared.data.models.story.event.condition.EventCondition

/**
 * An event or NPC on the map.
 *
 * @param enabled This event is enabled and visible.
 * @param key Used for identifying an event for relocating to somewhere else.
 *  Can be leaved `null` if this event doesn't need to be relocated by [Event.TeleportThisEventTo].
 * @param trigger Trigger of this event.
 * @param lineNumber The line number of this event. Null means this event is not showing on the map,
 *  but still executed when enabled is true and triggered.
 * @param display The displaying of this event on the map. Null means displayed in default line.
 * @param blocksUser This event will block user's path on the map.
 * @param event Event.
 */
@Serializable
data class MapEvent(
    val enabled: EventCondition,
    val key: String? = null,
    val trigger: Trigger,
    val lineNumber: Int? = null,
    val display: String? = null,
    val blocksUser: EventCondition = EventCondition.False,
    val event: Event,
) {

    /**
     * The trigger of this event.
     */
    enum class Trigger {
        /**
         * When the user is entering this map.
         */
        ENTERED,

        /**
         * When the user moved to the same line of this event.
         */
        OVERLAPPED,

        /**
         * When the user interacted with this event.
         */
        INTERACTED,
    }

}
