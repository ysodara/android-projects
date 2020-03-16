package com.sodaray.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class showProfile : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.my_profile){
            val intent = Intent(this, showProfile::class.java)

            startActivity(intent)
        } else if (item.itemId == R.id.tv){
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        } else {
            mAuth = FirebaseAuth.getInstance()
            logoutUser()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    private fun logoutUser()
    {
        mAuth!!.signOut()
        updateUI()

    }

    private fun updateUI() {
        val intent = Intent(this@showProfile, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
