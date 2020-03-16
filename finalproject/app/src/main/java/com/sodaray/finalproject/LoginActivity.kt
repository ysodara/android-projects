package com.sodaray.finalproject

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var etpassword: EditText
    lateinit var btnLogin : Button
    lateinit var btnCreate: Button

    private val TAG = "LoginActivity"

    private var email: String? = null
    private var password: String? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()

    }
        private fun initialize() {

            edtEmail = findViewById<View>(R.id.et_email) as EditText
            etpassword = findViewById<View>(R.id.et_password) as EditText
            btnLogin = findViewById<View>(R.id.btn_login) as Button
            btnCreate = findViewById<View>(R.id.btn_register_account) as Button
            mAuth = FirebaseAuth.getInstance()
            btnCreate!!
                .setOnClickListener { startActivity(Intent(this,
                    RegisterActivity::class.java)) }
            btnLogin!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        email = edtEmail?.text.toString()
        password = etpassword?.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            Log.d(TAG, "Logging in user.")
            mAuth!!.signInWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        updateUI()
                    } else {
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
