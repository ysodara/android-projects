package com.sodaray.assignment4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var password: EditText
    lateinit var btnLogin : Button
    lateinit var btnCreate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreference:SharedPreference = SharedPreference(this)

        edtEmail = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnCreate = findViewById(R.id.btn_register_account)


        btnLogin.setOnClickListener {
            val email = edtEmail.editableText.toString()
            val passwords = password.editableText.toString()

            val saveEmail = sharedPreference.getValueString("email")
            val savePassword = sharedPreference.getValueString("password")

            if (email == saveEmail && passwords == savePassword){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this@LoginActivity,"Wrong Credentials", Toast.LENGTH_SHORT).show()
            }

        }
        btnCreate.setOnClickListener { val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent) }
    }
}
