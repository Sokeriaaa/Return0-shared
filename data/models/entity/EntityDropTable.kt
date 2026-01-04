package sokeriaaa.return0.shared.data.models.entity

import kotlinx.serialization.Serializable
import kotlin.random.Random

/**
 * The entity drop table.
 *
 * A linear table based on enemy level.
 */
@JvmInline
@Serializable
value class EntityDropTable(val value: List<DropEntry>) {

    /**
     * Generate the rewards of defeating an enemy.
     */
    fun generateRewards(
        enemyLevel: Int,
        random: Random = Random,
    ): Map<String, Int> = value.associate {
        it.itemKey to it.roll(enemyLevel, random)
    }

    /**
     * Drop entry.
     *
     * Final drop count is ([levelBonus] * enemyLevel + [base]) * (random.nextFloat() * [floating] * 2 + 1 - [floating]).
     * Then round down to Int.
     */
    @Serializable
    data class DropEntry(
        val itemKey: String,
        val base: Float = 0F,
        val levelBonus: Float,
        val floating: Float = 0.25F,
        val max: Int = Int.MAX_VALUE,
    ) {
        fun roll(
            enemyLevel: Int,
            random: Random = Random,
        ): Int {
            return ((levelBonus * enemyLevel + base) * (random.nextFloat() * floating * 2 + 1 - floating))
                .toInt().coerceIn(0..max)
        }
    }
}