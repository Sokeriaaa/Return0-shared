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
enum class EntityPath {
    /**
     * Unspecified: A placeholder path, can be used on enemies or testing placeholders.
     */
    UNSPECIFIED,

    /**
     * Heap: Sustained & Self-Boost, sometimes AoE.
     */
    HEAP,

    /**
     * Thread: Aggressive Single-Target, rapid actions.
     */
    THREAD,

    /**
     * Overclock: Burst Utility & High damage.
     */
    OVERCLOCK,

    /**
     * Sandbox: Control & Crowd Manipulation
     */
    SANDBOX,

    /**
     * Protocol: Support & Buffing
     */
    PROTOCOL,

    /**
     * Daemon: Healing & Regeneration
     */
    DAEMON,

    /**
     * Kernel: Tank & Defender, shielding.
     */
    KERNEL,

    /**
     * Runtime: Sustained Damage & DoT
     */
    RUNTIME,
}