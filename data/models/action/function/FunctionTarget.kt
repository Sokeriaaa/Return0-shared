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

/**
 * State target selection for functions.
 */
sealed interface FunctionTarget {

    /**
     * Only take effect for the user.
     */
    data object Self : FunctionTarget

    /**
     * Select a single team mate (or self).
     */
    data object SingleTeamMate : FunctionTarget

    /**
     * Random of team mates. (non-selectable)
     */
    data class RandomTeamMates(val count: Int) : FunctionTarget

    /**
     * Applied to all team mates (include self)
     */
    data object AllTeamMates : FunctionTarget

    /**
     * Select a single enemy.
     */
    data object SingleEnemy : FunctionTarget

    /**
     * Random of enemies. (non-selectable)
     */
    data class RandomEnemies(val count: Int) : FunctionTarget

    /**
     * Applied to all enemies.
     */
    data object AllEnemies : FunctionTarget

    /**
     * Applied to the full arena except self (include all team mates and all enemies.)
     */
    data object FullArenaExceptSelf : FunctionTarget

    /**
     * Applied to the full arena (include self, all team mates and all enemies.)
     */
    data object FullArena : FunctionTarget

}