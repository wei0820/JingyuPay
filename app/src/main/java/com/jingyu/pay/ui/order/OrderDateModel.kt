package com.jingyu.pay.ui.order

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class OrderDateModel {


    var BaseUrl : String = "https://api2.channel-sign.com/"
    fun getMerchantPublicOrders(token:String,orderResponse: OrderResponse){
        var jsonObject= JSONObject()
        jsonObject.put("token",token)
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()
        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BaseUrl + "api/android/MerchantOrders/GetMerchantPublicOrders")
            .post(requestBody)
            .header("content-type","application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                orderResponse.getFailure(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                orderResponse.getResponse( response.body?.string()!!)
            }
        })

    }
    fun test(token:String,orderResponse: OrderResponse){
        Log.d("Jack","test");

        var jsonObject= JSONObject()
        jsonObject.put("token",token)
        var jsonStr=jsonObject.toString()
        val contentType: MediaType = "application/json".toMediaType()
        //调用请求
        val requestBody = jsonStr.toRequestBody(contentType)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BaseUrl + "api/user/pendingPush?")
            .get()
            .header("content-type","application/json")
            .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOiIxNjkwNTUxMzI4IiwiZXhwIjoxNjkwNzMxMzI4LCJpZCI6IjJiOTcyMjRhLWQ4M2MtNGQ5YS1iMjRhLWNkZWE4ZTA3ZjdmYyIsImxvZ2luSWQiOiJjZXNoaSIsImlwIjoiMTIyLjExNi4yMi40OCIsInJvbGVOYW1lIjoi5Lya5ZGYIiwiaXNzIjoiR29vZ2xlIiwiYXVkIjoid2ViIn0.Ni7vZrS2-T7TczFDqWbWdc6WxvrsFzGCDrO3WnT_4IA")
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


    interface OrderResponse{
        fun getResponse(s : String)
        fun getFailure(s: String)
    }
}