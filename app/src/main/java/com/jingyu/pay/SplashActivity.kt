package com.jingyu.pay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        //啟動執行序
        Thread {
            try {
                Thread.sleep(2000)
                JumpPage()

            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()


    }

    fun JumpPage(){
        val intent  = Intent()
        intent.setClass(this,LoginActivity::class.java)
        startActivity(intent)
    }

}