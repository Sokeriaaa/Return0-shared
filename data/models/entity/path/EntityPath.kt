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
package sokeriaaa.return0.shared.data.models.entity.path

/**
 * The entity path.
 */
enum class EntityPath(val icon: String) {
    /**
     * Unspecified: A placeholder path, can be used on enemies or testing placeholders.
     */
    UNSPECIFIED(""),

    /**
     * Heap: Sustained & Self-Boost, sometimes AoE.
     */
    HEAP("\uD83D\uDEE2\uFE0F"),

    /**
     * Thread: Aggressive Single-Target, rapid actions.
     */
    THREAD("\u26D3\uFE0F"),

    /**
     * Overclock: Burst Utility & High damage.
     */
    OVERCLOCK("\uD83D\uDCA5"),

    /**
     * Sandbox: Control & Crowd Manipulation
     */
    SANDBOX("\uD83E\uDDF0"),

    /**
     * Protocol: Support & Buffing
     */
    PROTOCOL("\uD83D\uDD11"),

    /**
     * Daemon: Healing & Regeneration
     */
    DAEMON("\uD83D\uDC9A"),

    /**
     * Kernel: Tank & Defender, shielding.
     */
    KERNEL("\uD83D\uDEE1\uFE0F"),

    /**
     * Runtime: Sustained Damage & DoT
     */
    RUNTIME("\u2699\uFE0F"),
}