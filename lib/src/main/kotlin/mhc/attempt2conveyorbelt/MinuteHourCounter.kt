package mhc.attempt2conveyorbelt


import java.time.LocalDateTime

/**
 * Track the cumulative counts over the past minute and over the past hour.
 * Useful, for example, to track recent bandwidth usage.
 */
class MinuteHourCounter {

    private val minuteEvents: MutableList<Event> = mutableListOf()
    private val hourEvents: MutableList<Event> = mutableListOf()

    private var minuteCount: Int = 0
    private var hourCount: Int = 0

    /**
     * Add a new data point (count >= 0).
     * For the next minute, MinuteCount() will be larger by +count.
     *  For the next hour, HourCount() will be larger by +count.
     */
    fun add(count: Int) {
        val nowSecs = LocalDateTime.now()
        shiftOldEvents(nowSecs)
        // Feed into the minute list (not into the hour list--that will happen later)
        minuteEvents.add(Event(count, nowSecs))
        minuteCount += count
        hourCount += count
    }

    /**
     * Return the accumulated count over the past 60 seconds.
     */
    fun minuteCount(): Int {
        shiftOldEvents(LocalDateTime.now())
        return minuteCount
    }

    /**
     * Return the accumulated count over the past 3600 seconds.
     */
    fun hourCount(): Int {
        shiftOldEvents(LocalDateTime.now())
        return hourCount
    }

    fun shiftOldEvents(nowSecs: LocalDateTime) {
        val minuteAgo = nowSecs.minusMinutes(1)
        val hourAgo = nowSecs.minusHours(1)

        while (minuteEvents.isNotEmpty() && minuteEvents[0].time <= minuteAgo) {
            hourEvents.add(minuteEvents[0])
            minuteCount -= minuteEvents[0].count
            minuteEvents.removeAt(0)
        }
        while (hourEvents.isNotEmpty() && hourEvents[0].time <= hourAgo) {
            hourCount -= hourEvents[0].count
            hourEvents.removeAt(0)
        }
    }
}


data class Event(val count: Int, val time: LocalDateTime)
