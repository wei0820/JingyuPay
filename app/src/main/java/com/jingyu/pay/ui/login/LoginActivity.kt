package com.jingyu.pay.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.BasicActivity
import com.jingyu.pay.MainActivity
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.R
import com.jingyu.pay.ui.home.HomeViewModel
import com.jingyu.pay.ui.home.HomeViewModelFactory
import com.tools.payhelper.ui.login.LoginViewModelFactory

lateinit var loginButton: Button
class LoginActivity : BasicActivity() {
    val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.loginbtn)
        loginButton.setOnClickListener {
            startActivity(Intent().setClass(this, MainActivity::class.java))
        }
        loginViewModel.getUserToken("ceshi","1b8bc9a66a77dcf8208a7146c4d23ae8","395039").observe(this, Observer {
            PayHelperUtils.saveUserLoginToken(this,it.data.token)
            PayHelperUtils.saveUserLoginName(this,"ceshi")

        })

    }
}