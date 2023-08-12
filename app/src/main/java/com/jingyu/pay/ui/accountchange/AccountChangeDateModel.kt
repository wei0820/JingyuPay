package com.jingyu.pay.ui.accountchange

import android.content.Context
import android.util.Log
import com.jingyu.pay.Constant
import com.jingyu.pay.PayHelperUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AccountChangeDateModel {


    var BaseUrl : String = Constant.API_URL
    fun getAccountChangeDate(context: Context,accountCahngeResponse: AccountCahngeResponse){
        var jsonObject= JSONObject()
        jsonObject.put("token","")
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()
        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BaseUrl + "api/user/AccountChange")
            .get()
            .header("Authorization", "Bearer " + PayHelperUtils.getUserToken(context))
            .header("content-type","application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                accountCahngeResponse.getResponse( response.body?.string()!!)
            }
        })

    }

    interface AccountCahngeResponse{
        fun getResponse(s : String)
    }
}