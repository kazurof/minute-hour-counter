package mhc.attempt3timebucketed


import java.time.ZonedDateTime

/**
 * A class that keeps counts for the past N buckets of time.
 * Example: TrailingBucketCounter(30, 60) tracks the last 30 minute-buckets of time.
 */
class TrailingBucketCounter(numBuckets: Int, private val secsPerBucket: Int) {
    private val buckets: ConveyorQueue = ConveyorQueue(numBuckets)
    private var lastUpdateTime: ZonedDateTime = ZonedDateTime.now()

    // Calculate how many buckets of time have passed and Shift() accordingly.
    private fun update(now: ZonedDateTime) {
        val currentBucket: Long = now.toEpochSecond() / secsPerBucket
        val lastUpdateBucket: Long = lastUpdateTime.toEpochSecond() / secsPerBucket

        buckets.shift(currentBucket - lastUpdateBucket);
        lastUpdateTime = now;
    }


    fun add(count: Int, now: ZonedDateTime) {
        update(now)
        buckets.addToBack(count)
    }

    // Return the total count over the last num_buckets worth of time
    fun trailingCount(now: ZonedDateTime): Int {
        update(now)
        return buckets.totalSum()
    }
}
