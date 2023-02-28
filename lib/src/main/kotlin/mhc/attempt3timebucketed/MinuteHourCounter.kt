package mhc.attempt3timebucketed

import java.time.ZonedDateTime

/**
 * Track the cumulative counts over the past minute and over the past hour.
 * Useful, for example, to track recent bandwidth usage.
 */
class MinuteHourCounter {

    private val minuteCounts = TrailingBucketCounter(60, 1)
    private val hourCounts = TrailingBucketCounter(60, 60)

    /**
     * Add a new data point (count >= 0).
     * For the next minute, MinuteCount() will be larger by +count.
     *  For the next hour, HourCount() will be larger by +count.
     */
    fun add(count: Int) {
        val now = ZonedDateTime.now()
        minuteCounts.add(count, now)
        hourCounts.add(count, now)
    }

    /**
     * Return the accumulated count over the past 60 seconds.
     */
    fun minuteCount(): Int {
        val now = ZonedDateTime.now()
        return minuteCounts.trailingCount(now)
    }

    /**
     * Return the accumulated count over the past 3600 seconds.
     */
    fun hourCount(): Int {
        val now = ZonedDateTime.now()
        return hourCounts.trailingCount(now)
    }
}
