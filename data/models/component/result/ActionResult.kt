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
package sokeriaaa.return0.shared.data.models.component.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Action results.
 */
@Serializable
sealed interface ActionResult {

    /**
     * Index of the entity array who executes the action.
     */
    val fromIndex: Int

    /**
     * Index of the entity array whom this action executes at.
     */
    val toIndex: Int

    /**
     * Whether this action result is valid.
     */
    fun isValid(): Boolean

    /**
     * Damage
     *
     * @param finalDamage The final damage target took.
     * @param damageCoerced Final damage coerced at most for target's HP before taking damage.
     * @param effectiveness Effectiveness for categories, generally -2~2. strong +1, weak -1.
     * @param isCritical Whether this attack is critical or not.
     */
    @Serializable
    @SerialName("Damage")
    data class Damage(
        override val fromIndex: Int,
        override val toIndex: Int,
        val finalDamage: Int,
        val damageCoerced: Int,
        val effectiveness: Int,
        val isCritical: Boolean,
    ) : ActionResult {
        override fun isValid(): Boolean = finalDamage > 0
    }

    /**
     * Heal
     *
     * @param finalHeal The final heal target got.
     * @param healCoerced Final heal coerced at most for target's lost HP before healing.
     */
    @Serializable
    @SerialName("Heal")
    data class Heal(
        override val fromIndex: Int,
        override val toIndex: Int,
        val finalHeal: Int,
        val healCoerced: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = finalHeal > 0
    }

    /**
     * SP change.
     */
    @Serializable
    @SerialName("SpChange")
    data class SpChange(
        override val fromIndex: Int,
        override val toIndex: Int,
        val finalChange: Int,
        val changeCoerced: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = finalChange != 0
    }

    /**
     * AP change.
     */
    @Serializable
    @SerialName("ApChange")
    data class ApChange(
        override val fromIndex: Int,
        override val toIndex: Int,
        val finalChange: Int,
        val changeCoerced: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = finalChange != 0
    }

    /**
     * Attached/Overrode an effect to target.
     */
    @Serializable
    @SerialName("AttachEffect")
    data class AttachEffect(
        override val fromIndex: Int,
        override val toIndex: Int,
        val effectName: String,
        val effectTier: Int = 1,
        val turns: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = true
    }

    /**
     * Removed an effect from target.
     *
     * @param originalIndex The index of whom attached this effect to the target.
     */
    @Serializable
    @SerialName("RemoveEffect")
    data class RemoveEffect(
        override val fromIndex: Int,
        override val toIndex: Int,
        val originalIndex: Int,
        val effectName: String,
    ) : ActionResult {
        override fun isValid(): Boolean = true
    }

    /**
     * A missed attack.
     */
    @Serializable
    @SerialName("Missed")
    data class Missed(
        override val fromIndex: Int,
        override val toIndex: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = true
    }

    /**
     * Action successfully executed but had no effect or be nullified.
     */
    @Serializable
    @SerialName("NoEffect")
    data class NoEffect(
        override val fromIndex: Int,
        override val toIndex: Int,
    ) : ActionResult {
        override fun isValid(): Boolean = true
    }
}