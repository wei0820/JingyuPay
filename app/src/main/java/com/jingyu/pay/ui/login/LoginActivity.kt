package com.jingyu.pay.ui.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.client.android.MNScanManager
import com.jingyu.pay.*
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
        requestCameraPerm()

        loginButton.setOnClickListener {


            //需要判断有没有权限

            //需要判断有没有权限
            MNScanManager.startScan(this
            ) { resultCode, data -> handlerResult(resultCode, data) }


//            var loginid = edt.text.toString()
//            var password = edt2.text.toString()
//            var code = edt3.text.toString()
//            if (loginid.isEmpty()){
//                ToastManager.showToastCenter(this,"帐号不得为空")
//                return@setOnClickListener
//
//            }else if (password.isEmpty()){
//                ToastManager.showToastCenter(this,"密码不得为空")
//
//                return@setOnClickListener
//
//            }else if (code.isEmpty()){
//                ToastManager.showToastCenter(this,"验证码不得为空")
//
//                return@setOnClickListener
//
//            }
//            Log.d("Jack",PayHelperUtils.md5(password))
//            loginViewModel.getUserToken(loginid,PayHelperUtils.md5(password),code).observe(this, Observer {
//                if (it!=null){
//                    runOnUiThread {
//                        ToastManager.showToastCenter(this,it.msg)
//
//                        PayHelperUtils.saveUserLoginToken(this,it.data.token)
//                        PayHelperUtils.saveUserLoginName(this,loginid)
//                    }
//                }
//
//
//            })
//
//
//
//            startActivity(Intent().setClass(this, MainActivity::class.java))
//        }
        }

    }
    fun requestCameraPerm() {
        //判断权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 10010)
            }
        }
    }

    private fun handlerResult(resultCode: Int, data: Intent?) {
        if (data == null) {
            return
        }
        Log.d("Jack",resultCode.toString())

        when (resultCode) {

                    MNScanManager.RESULT_SUCCESS -> {
                val resultSuccess = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_SUCCESS)
                ToastManager.showToastCenter(this,"扫描结果显示：$resultSuccess")
            }
            MNScanManager.RESULT_FAIL -> {
                val resultError = data.getStringExtra(MNScanManager.INTENT_KEY_RESULT_ERROR)
                ToastManager.showToastCenter(this,""+resultError)
            }
            MNScanManager.RESULT_CANCLE ->ToastManager.showToastCenter(this,"取消扫码")
            else -> {}
        }
    }

}