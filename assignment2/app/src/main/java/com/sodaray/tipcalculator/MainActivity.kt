package com.sodaray.tipcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    var tips = 0.0
    var toPay = 0.0
    var toPayPerPerson = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate_btn.setOnClickListener({
            showInfo()
            hideKeyboard(it)

        })

        reset_btn.setOnClickListener({
            resetInfo()
            hideKeyboard(it)
        })
        exit_btn.setOnClickListener({ finishAffinity() })


        }
    private fun resetInfo(){
        amountOfBill.text.clear()
        tipPercentage.text.clear()
        numberOfPeople.text.clear()
        tipAmountTextView.text = "0"
        totalToPayTextView.text = "0"
        totalPerPersonTextView.text = "0"
    }
    private fun showInfo() {
        if (amountOfBill.text!!.isNotEmpty() && tipPercentage.text!!.isNotEmpty() && numberOfPeople.text!!.isNotEmpty()) {
            CalculateAll()
        }
    }
    /*private fun showInfo() {
        if (amountOfBill.text!!.isNotEmpty() && tipPercentage.text!!.isNotEmpty() && numberOfPeople.text!!.isNotEmpty()) {
            val tips: Double? = getTip()
            val total: Double? = getTotal()
            val perPerson: Double? = getPerPerson()
            tipAmountTextView.text = String.format("%.2f", tips)
            totalToPayTextView.text = String.format("%.2f", total)
            totalPerPersonTextView.text = String.format("%.2f", perPerson)

        }
    }*/
    private fun hideKeyboard(view: View?) {
        view?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    private fun CalculateAll() {
        val billAmountInput = amountOfBill.text.toString().toDouble()
        val tipInput = tipPercentage.text.toString().toDouble()
        val numPeople = numberOfPeople.text.toString().toDouble()
        tips  = TipCalculate().getTip(billAmountInput, tipInput)
        toPay = billAmountInput + tips
        toPayPerPerson = toPay / numPeople
        tipAmountTextView.text = String.format("%.2f", tips)
        totalToPayTextView.text = String.format("%.2f", toPay)
        totalPerPersonTextView.text = String.format("%.2f", toPayPerPerson)
    }

    /*private fun getTotal(): Double {
        val billAmountInput  = amountOfBill.text.toString().toDouble()
        val tip = getTip()
        val total  = billAmountInput + tip
        return total
    }
    private fun getPerPerson(): Double {

        val TotalAndTip = getTotal()
        val numPeople = numberOfPeople.text.toString().toDouble()
        val perPerson = TotalAndTip / numPeople
        return perPerson
    }*/

    }

