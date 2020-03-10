package com.sodaray.assignment3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText

class Welcome : AppCompatActivity(){

    lateinit var edtName: EditText
    lateinit var edtEmail: EditText
    lateinit var btnSave: Button
    lateinit var btnRetrive: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)


    }
}