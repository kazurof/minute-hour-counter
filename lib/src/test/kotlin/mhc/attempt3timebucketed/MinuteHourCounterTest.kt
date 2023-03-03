package mhc.attempt3timebucketed

import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

class MinuteHourCounterTest {
    @Test
    fun testTypicalCase() {
        val sut = MinuteHourCounter()
        sut.add(1)
        assertEquals(1, sut.hourCount())
        sut.add(10)
        assertEquals(11, sut.hourCount())
        sut.add(23)
        assertEquals(34, sut.minuteCount())
        TimeUnit.SECONDS.sleep(61)
        assertEquals(0, sut.minuteCount())
        assertEquals(34, sut.hourCount())
    }
}
