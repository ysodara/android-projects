package com.sodaray.assignment3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sodaray.assignment3.model.SharedPreference

class RegisterActivity : AppCompatActivity() {

    //et_first_name et_last_name et_email et_password btn_register

    lateinit var edtFirstName: EditText
    lateinit var edtLastName: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnCreate: Button
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val sharedPreference:SharedPreference = SharedPreference(this)

        edtFirstName = findViewById(R.id.et_first_name)
        edtLastName = findViewById(R.id.et_last_name)
        edtEmail = findViewById(R.id.et_email)
        edtPassword = findViewById(R.id.et_password)
        btnCreate = findViewById(R.id.btn_register)

        btn = findViewById(R.id.retrieve)

        btnCreate.setOnClickListener {
            val firstName = edtFirstName.editableText.toString()
            val lastName = edtLastName.editableText.toString()
            val email = edtEmail.editableText.toString()
            val password = edtPassword.editableText.toString()

            sharedPreference.save("firstName",firstName)
            sharedPreference.save("lastName",lastName)
            sharedPreference.save("email",email)
            sharedPreference.save("password",password)
            startActivity(Intent(this, MainActivity::class.java))

        }


        btn.setOnClickListener{
            Toast.makeText(this@RegisterActivity,sharedPreference.getValueString("password"),Toast.LENGTH_SHORT).show()
        }
    }
}