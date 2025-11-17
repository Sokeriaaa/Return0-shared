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
package sokeriaaa.return0.shared.common.helpers

import kotlinx.serialization.json.Json

object JsonHelper {

    val json = Json {
        ignoreUnknownKeys = true
    }

    inline fun <reified T> encodeToJsonString(
        value: T,
    ): String {
        return json.encodeToString(
            value = value,
        )
    }

    inline fun <reified T> decodeFromString(
        string: String,
    ): T {
        return json.decodeFromString(
            string = string,
        )
    }

    inline fun <reified T> T.toJsonString(): String =
        json.encodeToString(value = this)
}