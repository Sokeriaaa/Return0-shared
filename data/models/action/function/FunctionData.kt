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

import kotlinx.serialization.Serializable
import sokeriaaa.return0.models.entity.category.Category
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.component.values.Value

/**
 * Data class for a function (skill).
 *
 * @param name Function name.
 * @param category Category of this function.
 * @param target Target selection of this function.
 * @param bullseye This function is guaranteed to hit the target, can not be missed.
 *  (for functions with positive power)
 * @param basePower Base power of this function.
 *  If this value is negative, that means this function is for healing.
 * @param powerBonus Power bonus for each tier of this function.
 *  final power = `basePower + powerBonus * (tier - 1)`.
 * @param baseSPCost SP cost of this function. If the user's SP is insufficient,
 *  this function cannot be executed.
 * @param spCostBonus SP cost bonus for each tier of this function.
 *  final spCost = `baseSPCost + spCostBonus * (tier - 1)`.
 * @param growth Levels of the entity required to unlock certain tiers of this function.
 *  Starts from tier 1, for example, when the entity's level reaches `growth[1]`,
 *  this function will automatically upgrade to tier 2. `growth[0]` means the minimum level
 *  of entity required to unlock this function.
 *  **Supports** empty list, which will mean this function is always unlocked and cannot be
 *  upgraded.
 * @param attackModifier Modifier for a damage/healing function, can be null.
 *  Some parameters is not applicable for healing. No effect on Effects.
 * @param extra Extra effects would apply to the target upon each successful attack/healing.
 *  Will apply multiple times when [AttackModifier.attackTimes] is greater than 1.
 */
@Serializable
data class FunctionData(
    val name: String,
    val category: Category,
    val target: FunctionTarget,
    val bullseye: Boolean,
    val basePower: Int,
    val powerBonus: Int,
    val baseSPCost: Int,
    val spCostBonus: Int,
    val growth: List<Int>?,
    val attackModifier: AttackModifier? = null,
    val extra: Extra? = null,
) {
    /**
     * Modifier for an attacking function.
     *
     * @param attackTimes Allows a function attack/heal multiple times in one execution.
     * @param actualPower Overrides [FunctionData.basePower].
     * @param targetRateOffset Offset for target rate. No effect for healing.
     * @param criticalRateOffset Offset for critical hit rate. No effect for healing.
     * @param criticalDMGOffset Offset for critical hit damage. No effect for healing.
     * @param userBaseATKOverride Overrides user's baseATK when calculating damage.
     * @param targetBaseDEFOverride Overrides target's baseDEF when calculating damage.
     *  No effect for healing.
     */
    @Serializable
    data class AttackModifier(
        val attackTimes: Value? = null,
        val actualPower: Value? = null,
        val targetRateOffset: Value? = null,
        val criticalRateOffset: Value? = null,
        val criticalDMGOffset: Value? = null,
        val userBaseATKOverride: Value? = null,
        val targetBaseDEFOverride: Value? = null,
    )
}