package sokeriaaa.return0.shared.data.api.component.condition

import sokeriaaa.return0.shared.data.models.component.conditions.Condition
import sokeriaaa.return0.shared.data.models.component.extras.CommonExtra
import sokeriaaa.return0.shared.data.models.component.extras.Extra
import sokeriaaa.return0.shared.data.models.component.values.CommonValue
import sokeriaaa.return0.shared.data.models.component.values.Value
import sokeriaaa.return0.shared.data.models.story.event.Event

inline fun valueCases(
    scope: CasedValueExecutor.() -> Unit,
): CommonValue.Cased {
    val executor = CasedValueExecutor()
    executor.scope()
    return CommonValue.Cased(
        cases = executor.cases,
        defaultValue = executor.defaultValue,
    )
}

inline fun extraCases(
    scope: CasedExtraExecutor.() -> Unit,
): CommonExtra.Cased {
    val executor = CasedExtraExecutor()
    executor.scope()
    return CommonExtra.Cased(
        cases = executor.cases,
        defaultExtra = executor.defaultExtra,
    )
}

inline fun eventCases(
    scope: CasedEventExecutor.() -> Unit,
): Event.Cased {
    val executor = CasedEventExecutor()
    executor.scope()
    return Event.Cased(
        cases = executor.cases,
        defaultEvent = executor.defaultEvent,
    )
}

class CasedValueExecutor @PublishedApi internal constructor(
    val cases: MutableList<Pair<Condition, Value?>> = ArrayList(),
    var defaultValue: Value? = null,
) {
    infix fun Condition.then(value: Value?) {
        cases.add(this to value)
    }

    fun onDefault(value: Value?) {
        defaultValue = value
    }
}

class CasedExtraExecutor @PublishedApi internal constructor(
    val cases: MutableMap<Condition, Extra?> = HashMap(),
    var defaultExtra: Extra? = null,
) {
    infix fun Condition.then(extra: Extra?) {
        cases[this] = extra
    }

    fun onDefault(extra: Extra?) {
        defaultExtra = extra
    }
}

class CasedEventExecutor @PublishedApi internal constructor(
    val cases: MutableMap<Condition, Event?> = HashMap(),
    var defaultEvent: Event? = null,
) {
    infix fun Condition.then(event: Event?) {
        cases[this] = event
    }

    fun onDefault(event: Event?) {
        defaultEvent = event
    }
}