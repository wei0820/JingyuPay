package com.jingyu.pay.ui.buyrecord

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jingyu.pay.ui.login.LoginData
import kotlinx.coroutines.launch

class BuyRecordViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var accountChangeDateModel = BuyRecodeDateModel()

    var  buyRecordData = MutableLiveData<BuyRecordData>()

    fun  getBuyRecordList(context: Context,date:String):LiveData<BuyRecordData>{

        accountChangeDateModel.getBuyRecordList(context,date, object : BuyRecodeDateModel.AccountCahngeResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var a = "{\n" +
                                "    \"code\": 0,\n" +
                                "    \"msg\": \"success\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": \"FC93DE5D-B426-46FD-A5D3-0EFF5857B97D\",\n" +
                                "            \"orderNo\": \"21071601230506221529001g\",\n" +
                                "            \"bankName\": \"安徽省农村信用社\",\n" +
                                "            \"subName\": \"NA 安徽 六安\",\n" +
                                "            \"userName\": \"孙仲玉\",\n" +
                                "            \"cardId\": \"6217788305802345723\",\n" +
                                "            \"paymentOrderId\": \"C02F25A3-C7D7-45FC-9CDA-45B7E47B4B93\",\n" +
                                "            \"paymentQueueId\": \"\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"state\": 0,\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"commission\": 0.000000,\n" +
                                "            \"score\": 2000,\n" +
                                "            \"remark\": \"\",\n" +
                                "            \"created\": \"2023-08-11T00:17:54.363\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"count\": 0\n" +
                                "}\n"
                        var data = Gson().fromJson(a,BuyRecordData::class.java)
                        if (data != null){
                            buyRecordData.value = data
                        }

                    }
                }

            }

        })


        return buyRecordData
    }


}