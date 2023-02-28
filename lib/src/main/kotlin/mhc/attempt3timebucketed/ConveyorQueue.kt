package mhc.attempt3timebucketed

class ConveyorQueue(private var maxItems: Int) {
    /** sum of all items in q */
    private var totalSum: Int = 0
    private var q: ArrayDeque<Int> = ArrayDeque()

    fun totalSum(): Int {
        return totalSum
    }

    fun shift(numShifted: Long) {
        // In case too many items shifted, just clear the queue.
        if (numShifted >= maxItems) {
            q = ArrayDeque(); // clear the queue
            totalSum = 0;
            return;
        }
        // Push all the needed zeros.
        var localNumShifted = numShifted
        while (localNumShifted > 0) {
            q.add(0);
            localNumShifted--
        }
        // Let all the excess items fall off.
        while (q.size > maxItems) {
            totalSum -= q[0];
            q.removeFirst()
        }
    }

    fun addToBack(count: Int) {
        if (q.size == 0) shift(1) // Make sure q has at least 1 item.
        q[q.size - 1] += count
        totalSum += count;
    }

}
