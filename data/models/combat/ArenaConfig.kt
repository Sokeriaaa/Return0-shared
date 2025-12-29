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
package sokeriaaa.return0.shared.data.models.combat

import kotlinx.serialization.Serializable

/**
 * Arena config, uses to start a combat.
 *
 * @param saveStatus The entity status after the combat should be saved.
 * @param temporaryEntities Temporary parties in this combat. If [saveStatus] is true, their status
 *                          should not be saved.
 * @param difficulty Difficulty modifier for this combat. Affects the EXP and token
 *                   rewards after winning the combat. ([Mode.COMMON] only)
 */
@Serializable
data class ArenaConfig(
    val mode: Mode,
    val saveStatus: Boolean,
    val parties: List<PartyState>,
    val enemies: List<EnemyState>,
    val difficulty: Float = 1F,
    val temporaryEntities: Set<String> = emptySet(),
    // TODO to add a event list here.
) {

    @Serializable
    enum class Mode {
        /**
         * Common combat in the game progress.
         */
        COMMON,

        /**
         * Emulator mode for experimental proposes.
         */
        EMULATOR,
    }
}