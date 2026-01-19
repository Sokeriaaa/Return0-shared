/**
 * Copyright (C) 2026 Sokeriaaa
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
package sokeriaaa.return0.shared.data.models.entity.plugin

import kotlinx.serialization.Serializable
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.entity.path.EntityPath

/**
 * Plugin (Weapon) data.
 *
 * @param path Entity path of this plugin. If the path of entity and plugin are not identical,
 *             only const values will take effect, other effects are disabled.
 * @param onAttack Executes when this entity attacked successfully on an enemy.
 * @param onDefend Executes when this entity is attacked successfully by an enemy.
 *                 In this extra, the `user` is current entity, the `target` is the attacker.
 * @param attackRateOffset Damage multiplier offset when attacking enemy.
 * @param defendRateOffset Damage multiplier offset when being attacked by an enemy.
 */
@Serializable
data class PluginData(
    val key: String,
    val nameRes: String,
    val descriptionRes: String,
    val path: EntityPath,
    val onAttack: Extra? = null,
    val onDefend: Extra? = null,
    val attackRateOffset: Value? = null,
    val defendRateOffset: Value? = null,
)
