package com.sodaray.tipcalculator



class TipCalculate{

    fun getTip(billAmountInput : Double, tipInput : Double ): Double {
        val tips  = billAmountInput * (tipInput/100)
        return tips
    }
}