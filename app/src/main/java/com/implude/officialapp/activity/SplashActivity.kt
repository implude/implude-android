package com.implude.officialapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.implude.officialapp.custom.CupertinoDialog


class SplashActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("firstLogin", 0)
        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (FirebaseAuth.getInstance().currentUser != null) {
            if (account != null) {
                FirebaseAuth.getInstance().currentUser!!.reauthenticate(GoogleAuthProvider.getCredential(account.idToken, null)
                ).addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        else {
            if (!sharedPreferences.getBoolean("first", false)) {
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

    }
}
