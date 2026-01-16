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
package sokeriaaa.return0.shared.data.models.story.map

import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.values.EventValue
import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Game map data. We use a "Kotlin file" to present a map.
 * This is a one-dimension map, use the line number as the coordinates.
 *
 * @param name Map/File name.
 * @param lines Total lines of this map/file.
 * @param buggyRange The ranges that the user may encounter bugs to start a combat.
 * @param buggyEntries Enemy teams may be encountered in the [buggyRange].
 * @param failedAnchor When the player is failed in this map, they will be teleported to this
 *                     position with HP slightly recovered.
 * @param events Events in this map.
 */
@Serializable
data class MapData(
    val name: String,
    val lines: Int,
    val buggyRange: List<Pair<Int, Int>>,
    val buggyEntries: List<BuggyEntry>,
    val failedAnchor: Pair<String, Int> = name to 1,
    val events: List<MapEvent>,
) {

    init {
        require(buggyRange.isEmpty() || buggyEntries.isNotEmpty()) {
            "When buggyRange is defined, at least one entry should be included in buggyEntries."
        }
    }

    /**
     * An enemy team the user may encounter in the buggy range.
     *
     * @param level The level of each enemy. This value is calculated independently for each enemy.
     *              If a randomed value is used here, the generated enemy team may have different
     *              levels.
     */
    @Serializable
    data class BuggyEntry(
        val enemies: List<String>,
        val level: Value.Event,
    ) {
        constructor(enemies: List<String>, difficulty: Int) :
                this(enemies, EventValue.EnemyLevel(difficulty))
    }

}