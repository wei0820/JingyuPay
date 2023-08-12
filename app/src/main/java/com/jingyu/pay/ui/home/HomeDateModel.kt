package com.jingyu.pay.ui.home

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


class HomeDateModel {


    var BaseUrl : String = Constant.API_URL


    fun setBuySetting(context: Context,orderResponse: BuyResponse){

        var jsonObject= JSONObject()
        jsonObject.put("token","")
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()


        val urlBuilder: HttpUrl.Builder = (BaseUrl + "api/user/PaymentQueue?").toHttpUrlOrNull()!!.newBuilder()
        urlBuilder.addQueryParameter("Paymentlimit", "5000")
        urlBuilder.addQueryParameter("PaymentMax", "10000")


        val url: String = urlBuilder.build().toString()

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
                Log.d("Jack",e.toString());


            }

            override fun onResponse(call: Call, response: Response) {
                orderResponse.getResponse( response.body?.string()!!)
            }
        })

    }






    fun getBuyOrederList(context: Context,orderResponse: BuyResponse){

        var jsonObject= JSONObject()
        jsonObject.put("token","")
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()
        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BaseUrl + "api/user/pendingPush?")
            .get()
            .header("content-type","application/json")
            .header("Authorization", "Bearer " + PayHelperUtils.getUserToken(context))
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Jack",e.toString());


            }

            override fun onResponse(call: Call, response: Response) {
                orderResponse.getResponse( response.body?.string()!!)
            }
        })

    }


    interface BuyResponse{
        fun getResponse(s : String)
        fun getFailure(s: String)
    }
}