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

        //로그인 되있는 상태에서 가입에서 해지 되었을때 로그인 해제하는 코드
        //근데 이거때문에 스플래시 때마다 Login Validation 검사하는건 좀 너무 오래걸리는데
        //원격으로 로그인 해제할 방법이 있으면 좋을 거 같다
        //firebaseAuth 쪽은 되는거 같은데 Google 쪽이 안되;;
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
