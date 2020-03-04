package com.implude.officialapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.implude.officialapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //val providers = arrayListOf(
        //    AuthUI.IdpConfig.GoogleBuilder().build())

        button_login.setOnClickListener {

        }
    }
}