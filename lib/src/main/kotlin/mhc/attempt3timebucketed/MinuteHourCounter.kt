package mhc.attempt3timebucketed

import java.time.ZonedDateTime

const val NUM_BUCKETS = 60

const val SEC_IN_HOUR = 60

/**
 * Track the cumulative counts over the past minute and over the past hour.
 * Useful, for example, to track recent bandwidth usage.
 */
class MinuteHourCounter {

    private val minuteCounts = TrailingBucketCounter(NUM_BUCKETS, 1)
    private val hourCounts = TrailingBucketCounter(NUM_BUCKETS, SEC_IN_HOUR)

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
