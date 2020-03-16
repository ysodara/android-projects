package com.sodaray.finalproject

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    lateinit var edtFirstName:EditText
    lateinit var edtLastName:EditText
    lateinit var edtEmail:EditText
    lateinit var edtPassword:EditText
    lateinit var btnRegister:Button

    lateinit var inProgressBar: ProgressBar

    private val TAG = "RegisterActivity"

    lateinit var firstNameDB: String
    lateinit var lastNameDB: String
    lateinit var emailDB: String
    lateinit var passwordDB: String


    lateinit var dbReference: DatabaseReference
    lateinit var database:FirebaseDatabase
    lateinit var dbAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initialize()

    }

    private fun initialize(){
        edtFirstName = findViewById(R.id.et_first_name)
        edtLastName = findViewById(R.id.et_last_name)
        edtEmail = findViewById(R.id.et_email)
        edtPassword = findViewById(R.id.et_password)
        btnRegister = findViewById(R.id.btn_register)

        inProgressBar = ProgressBar(this)

        database = FirebaseDatabase.getInstance()
        dbReference = database!!.reference.child("Users")
        dbAuth = FirebaseAuth.getInstance()

        btnRegister!!.setOnClickListener { createAccount() }
    }

    private fun createAccount(){
        firstNameDB = edtFirstName.editableText.toString()
        lastNameDB = edtLastName.editableText.toString()
        emailDB = edtEmail.editableText.toString()
        passwordDB = edtPassword.editableText.toString()

        if (!TextUtils.isEmpty(firstNameDB)){
            dbAuth!!
                .createUserWithEmailAndPassword(emailDB!!, passwordDB!!)
                .addOnCompleteListener(this) {
                    task ->
                    if (task.isSuccessful){
                        Log.d(TAG, "CreateUserWithEmail:success")
                        val userID = dbAuth!!.currentUser!!.uid
                        verifyEmail()

                        val currentUserDb = dbReference!!.child(userID)
                        currentUserDb.child("firstName").setValue(firstNameDB)
                        currentUserDb.child("lastName").setValue(lastNameDB)
                        updateUserInfoAndUI()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@RegisterActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    private fun updateUserInfoAndUI() {
        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun verifyEmail(){
        val mUser = dbAuth!!.currentUser
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@RegisterActivity,
                        "Verification email sent to " + mUser.getEmail(),
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(this@RegisterActivity,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}