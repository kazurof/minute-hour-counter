package mhc.attempt1naive

import java.time.LocalDateTime

/**
 * Track the cumulative counts over the past minute and over the past hour.
 * Useful, for example, to track recent bandwidth usage.
 */
class MinuteHourCounter {

    private val events: MutableList<Event> = mutableListOf()

    private fun countSince(cutOff: LocalDateTime): Int {
        var count = 0

        for (event in events.asReversed().iterator()) {
            if (event.time <= cutOff) {
                break
            }
            count += event.count
        }
        return count
    }

    /**
     * Add a new data point (count >= 0).
     * For the next minute, MinuteCount() will be larger by +count.
     *  For the next hour, HourCount() will be larger by +count.
     */
    fun add(count: Int) {
        events.add(Event(count, LocalDateTime.now()))
    }

    /**
     * Return the accumulated count over the past 60 seconds.
     */
    fun minuteCount(): Int {
        return countSince(LocalDateTime.now().minusMinutes(1))
    }

    /**
     * Return the accumulated count over the past 3600 seconds.
     */
    fun hourCount(): Int {
        return countSince(LocalDateTime.now().minusHours(1))
    }
}

data class Event(val count: Int, val time: LocalDateTime)
