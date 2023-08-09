package com.jingyu.pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jingyu.pay.R

lateinit var loginButton: Button
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.loginbtn)
        loginButton.setOnClickListener {
            startActivity(Intent().setClass(this,MainActivity::class.java))
        }
    }
}