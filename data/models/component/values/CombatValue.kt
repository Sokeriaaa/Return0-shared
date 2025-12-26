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
package sokeriaaa.return0.shared.data.models.component.values

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Combat")
sealed interface CombatValue : Value.Combat {
    /**
     * Calculate the [value] on the **user** of action.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Combat.ForUser")
    data class ForUser internal constructor(val value: Value.Combat) : CombatValue

    /**
     * Calculate the [value] but with **the user and the target swapped**.
     */
    @ConsistentCopyVisibility
    @Serializable
    @SerialName("Combat.Swapped")
    data class Swapped internal constructor(val value: Value.Combat) : CombatValue

    /**
     * Load a value in the action saved by
     *  [sokeriaaa.return0.shared.data.models.component.extras.CommonExtra.SaveValue].
     *
     * @see sokeriaaa.return0.shared.data.models.component.extras.CommonExtra.SaveValue
     * @see sokeriaaa.return0.models.action.Action.values
     */
    @Serializable
    @SerialName("Combat.LoadValue")
    data class LoadValue(
        val key: String,
        val defaultValue: Value.Combat? = null,
    ) : CombatValue

    /**
     * Returns the damage for the attack.
     *
     * Has no effect in a context without a damage result (always returns 0.)
     */
    @Serializable
    @SerialName("Combat.Damage")
    data object Damage : CombatValue

    /**
     * Returns the damage coerced to the target's current HP for the attack.
     *
     * Has no effect in a context without a damage result (always returns 0.)
     */
    @Serializable
    @SerialName("Combat.DamageCoerced")
    data object DamageCoerced : CombatValue

    /**
     * Returns the damage nullified by shields.
     *
     * Has no effect in a context without a damage result (always returns 0.)
     */
    @Serializable
    @SerialName("Combat.ShieldedDamage")
    data object ShieldedDamage : CombatValue
}