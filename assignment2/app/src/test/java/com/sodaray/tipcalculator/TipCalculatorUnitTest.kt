package com.sodaray.tipcalculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorUnitTest {
    @Test
    fun calculate_tipShouldEqualPercentageTimeBill() {

        val tipCal = TipCalculate()

        val tipAmount = tipCal.getTip(77.85, 12.00)

        assertEquals(9.34, tipAmount, 0.01)
    }
}
