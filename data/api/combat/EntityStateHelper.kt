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
package sokeriaaa.return0.shared.data.api.combat

import sokeriaaa.return0.shared.data.models.combat.EnemyState
import sokeriaaa.return0.shared.data.models.combat.EntityState
import sokeriaaa.return0.shared.data.models.combat.PartyState
import sokeriaaa.return0.shared.data.models.entity.EnemyRewardTable
import sokeriaaa.return0.shared.data.models.entity.EntityData
import sokeriaaa.return0.shared.data.models.entity.EntityGrowth

val EntityState.level: Int
    get() = when (this) {
        is EnemyState -> level
        is PartyState -> level
    }

val EntityState.entityData: EntityData
    get() = when (this) {
        is EnemyState -> enemyData.entityData
        is PartyState -> partyData.entityData
    }

val EntityState.growth: EntityGrowth
    get() = when (this) {
        is EnemyState -> enemyData.growth
        is PartyState -> partyData.growth
    }

val EntityState.rewardTable: EnemyRewardTable?
    get() = when (this) {
        is EnemyState -> enemyData.rewardTable
        is PartyState -> null
    }