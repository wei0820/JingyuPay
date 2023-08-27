package com.jingyu.pay.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.BasicActivity
import com.jingyu.pay.MainActivity
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.R
import com.jingyu.pay.ToastManager
import com.jingyu.pay.ui.home.HomeViewModel
import com.jingyu.pay.ui.home.HomeViewModelFactory
import com.tools.payhelper.ui.login.LoginViewModelFactory

lateinit var loginButton: Button
class LoginActivity : BasicActivity() {
    val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
    }
    lateinit var edt : EditText
    lateinit var edt2 : EditText
    lateinit var edt3 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton = findViewById(R.id.loginbtn)
        edt = findViewById(R.id.edt)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)

        loginButton.setOnClickListener {
            var loginid = edt.text.toString()
            var password = edt2.text.toString()
            var code = edt3.text.toString()
            if (loginid.isEmpty()){
                ToastManager.showToastCenter(this,"帐号不得为空")
                return@setOnClickListener

            }else if (password.isEmpty()){
                ToastManager.showToastCenter(this,"密码不得为空")

                return@setOnClickListener

            }else if (code.isEmpty()){
                ToastManager.showToastCenter(this,"验证码不得为空")

                return@setOnClickListener

            }
            Log.d("Jack",PayHelperUtils.md5(password))
            loginViewModel.getUserToken(loginid,PayHelperUtils.md5(password),code).observe(this, Observer {
                if (it!=null){
                    runOnUiThread {
                        ToastManager.showToastCenter(this,it.msg)

                        PayHelperUtils.saveUserLoginToken(this,it.data.token)
                        PayHelperUtils.saveUserLoginName(this,loginid)
                    }
                }


            })



            startActivity(Intent().setClass(this, MainActivity::class.java))
        }


    }
}