package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("firstLogin", 0)


        if (FirebaseAuth.getInstance().currentUser == null)
        {
            if (sharedPreferences.getBoolean("first", false)) {
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}
