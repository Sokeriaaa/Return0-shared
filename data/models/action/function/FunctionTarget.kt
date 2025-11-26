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
package sokeriaaa.return0.shared.data.models.action.function

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * State target selection for functions.
 */
@Serializable
sealed interface FunctionTarget {

    /**
     * Only take effect for the user.
     */
    @Serializable
    @SerialName("Self")
    data object Self : FunctionTarget

    /**
     * Select a single team mate (or self).
     */
    @Serializable
    @SerialName("SingleTeamMate")
    data object SingleTeamMate : FunctionTarget

    /**
     * Random of team mates. (non-selectable)
     */
    @Serializable
    @SerialName("RandomTeamMates")
    data class RandomTeamMates(val count: Int) : FunctionTarget

    /**
     * Applied to all team mates (include self)
     */
    @Serializable
    @SerialName("AllTeamMates")
    data object AllTeamMates : FunctionTarget

    /**
     * Select a single enemy.
     */
    @Serializable
    @SerialName("SingleEnemy")
    data object SingleEnemy : FunctionTarget

    /**
     * Random of enemies. (non-selectable)
     */
    @Serializable
    @SerialName("RandomEnemies")
    data class RandomEnemies(val count: Int) : FunctionTarget

    /**
     * Applied to all enemies.
     */
    @Serializable
    @SerialName("AllEnemies")
    data object AllEnemies : FunctionTarget

    /**
     * Applied to the full arena except self (include all team mates and all enemies.)
     */
    @Serializable
    @SerialName("FullArenaExceptSelf")
    data object FullArenaExceptSelf : FunctionTarget

    /**
     * Applied to the full arena (include self, all team mates and all enemies.)
     */
    @Serializable
    @SerialName("FullArena")
    data object FullArena : FunctionTarget

}