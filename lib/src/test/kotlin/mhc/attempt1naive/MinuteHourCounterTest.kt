package mhc.attempt1naive

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
    }
}
