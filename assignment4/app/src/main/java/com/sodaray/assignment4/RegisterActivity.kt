package com.sodaray.assignment4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    lateinit var edtFirstName:EditText
    lateinit var edtLastName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtPassword:EditText
    lateinit var btnRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val sharedPreference:SharedPreference = SharedPreference(this)

        edtFirstName = findViewById(R.id.et_first_name)
        edtLastName = findViewById(R.id.et_last_name)
        edtEmail = findViewById(R.id.et_email)
        edtPassword = findViewById(R.id.et_password)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            val firstName = edtFirstName.editableText.toString()
            val lastName = edtLastName.editableText.toString()
            val email = edtEmail.editableText.toString()
            val password = edtPassword.editableText.toString()

            sharedPreference.save("firstName",firstName)
            sharedPreference.save("lastName", lastName)
            sharedPreference.save("email", email)
            sharedPreference.save("password", password)

            Toast.makeText(this@RegisterActivity,"Data Stored", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }

    }
}