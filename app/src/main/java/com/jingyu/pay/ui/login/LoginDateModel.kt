package com.jingyu.pay.ui.login

import android.util.Log
import com.jingyu.pay.Constant
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginDateModel {

    fun setUserLogin(loginid:String,password:String,code:String,loginrResponse: LoginrResponse){
        Log.d("Jack",loginid)
        var jsonObject= JSONObject()
        jsonObject.put("loginid",loginid)
        jsonObject.put("password",password)
        jsonObject.put("code",code)
        jsonObject.put("roleName","会员")
        jsonObject.put("IP","125.119.224.148")
        jsonObject.put("version","v8")
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()
        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(Constant.API_URL + "api/auth")
            .post(requestBody)
            .header("content-type","application/json")
            .build()
        Log.d("Jack",Constant.API_URL + "api/auth")


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                loginrResponse.getResponse( response.body?.string()!!)
            }
        })

    }


    interface LoginrResponse{
        fun getResponse(s : String)
    }
}