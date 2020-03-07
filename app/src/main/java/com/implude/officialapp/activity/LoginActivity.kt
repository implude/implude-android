package com.implude.officialapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.implude.officialapp.R
import com.implude.officialapp.custom.CupertinoDialog
import com.implude.officialapp.model.UserModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(){
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var userData : UserModel

    companion object {
        private const val RC_SIGN_IN = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        button_login.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount, user : UserModel? = null) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        //TODO: 이부분 땜빵으로 Complete 되자마자 바로 그냥 추가하게 했는데 예외처리 해줘야함
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if(user != null)
            {
                user.mail = acct.email.toString()
                user.profile = acct.photoUrl.toString()

                db.collection("users")
                    .document(it.result?.user?.uid!!)
                    .set(user)

                startActivity(Intent(this, SetProfileActivity::class.java))
            }
            startActivity(Intent(this, MainActivity::class.java))

            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)

                db.collection("emails").document(account?.email!!).get().addOnSuccessListener { documentSnapshot ->
                    if(!documentSnapshot.exists())
                    {
                        CupertinoDialog(this@LoginActivity).show("죄송합니다", "Implude 동아리 부원으로 추가되지 않은 계정입니다")
                        AuthUI.getInstance()
                            .signOut(this)
                    } else {
                        userData = documentSnapshot.toObject(UserModel::class.java)!!

                        //TODO: Firebase 쪽 문제로 로그인 실패했을 경우 예외 처리 필요, 어떻게 할지는 나도 모르겠다
                        if(userData.profile.isNotEmpty())
                            firebaseAuthWithGoogle(account)
                        else
                            firebaseAuthWithGoogle(account, userData)
                    }
                }
            } catch (e: ApiException) {
                CupertinoDialog(this@LoginActivity).show("오류", "로그인 과정에서 오류가 발생했습니다\n해결을 위해 동장혹은 부동장에게 문의하세요")
            }
        }
    }
}