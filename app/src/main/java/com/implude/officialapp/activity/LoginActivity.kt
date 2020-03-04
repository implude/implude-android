package com.implude.officialapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    val firebaseAuth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    lateinit var userData : UserModel

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

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    if(!userData.uid.isBlank()) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this, SetProfileActivity::class.java))
                    }
                } else {
                    CupertinoDialog(this@LoginActivity).show("오류", "로그인 과정에서 오류가 발생했습니다\n동장혹은 부동장에게 문의하세요")
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)

                val docRef = db.collection("users")
                val query = docRef.whereEqualTo("mail", account!!.email)
                query.get().addOnSuccessListener { documents ->
                    if(documents.isEmpty)
                    {
                        CupertinoDialog(this@LoginActivity).show("죄송합니다", "Implude 동아리 부원으로 추가되지 않은 계정입니다")
                    } else {
                        userData = documents.documents[0].toObject(UserModel::class.java)!!
                        firebaseAuthWithGoogle(account)
                    }
                }
            } catch (e: ApiException) {
                CupertinoDialog(this@LoginActivity).show("오류", "로그인 과정에서 오류가 발생했습니다\n동장혹은 부동장에게 문의하세요")
            }
        }
    }
}