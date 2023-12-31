package com.jingyu.pay.ui.accountchange

import android.content.Context
import android.util.Log
import com.jingyu.pay.Constant
import com.jingyu.pay.PayHelperUtils
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AccountChangeDateModel {


    var BaseUrl : String = Constant.API_URL
    fun getAccountChangeDate(context: Context,accountChange: AccountCahngeResponse){

        var jsonObject= JSONObject()
        jsonObject.put("token","")
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()


        val urlBuilder: HttpUrl.Builder = (BaseUrl + "api/user/FrozenRecords?").toHttpUrlOrNull()!!.newBuilder()
        urlBuilder.addQueryParameter("date", "")
        val url: String = urlBuilder.build().toString()
        Log.d("Jack",url);

        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .get()
            .header("content-type","application/json")
            .header("Authorization", "Bearer " + PayHelperUtils.getUserToken(context))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                accountChange.getResponse( response.body?.string()!!)
            }
        })

    }

    interface AccountCahngeResponse{
        fun getResponse(s : String)
    }
}