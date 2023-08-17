package com.jingyu.pay.ui.dashboard

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jingyu.pay.ui.login.LoginData
import kotlinx.coroutines.launch

class SellViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }


    var sellDateModel = SellDateModel()
    var setSellSettingData = MutableLiveData<CollectionQueueData>()
    var setCloseSellSettingData = MutableLiveData<CollectionQueueOffData>()

    var mSellListData = MutableLiveData<SellListData>()
    var confirmData  = MutableLiveData<ConfirmData>()


    fun setSellSetting(context: Context) : LiveData<CollectionQueueData>{
        sellDateModel.setSellSetting(context, object : SellDateModel.SellResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var data = Gson().fromJson(s,CollectionQueueData::class.java)
                        if (data!=null){
                            setSellSettingData.value = data
                        }
                    }
                }
            }

        })

        return  setSellSettingData
    }

    fun  setCloseSellSetting(context: Context) : LiveData<CollectionQueueOffData>{
        sellDateModel.setCloseSellSetting(context, object : SellDateModel.SellResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var data = Gson().fromJson(s,CollectionQueueOffData::class.java)
                        if (data!=null){
                            setCloseSellSettingData.value = data
                        }
                    }
                }            }

        })



        return  setCloseSellSettingData
    }

    fun getSellList(context: Context)  : LiveData<SellListData>{

        sellDateModel.getSellList(context, object : SellDateModel.SellResponse {
            override fun getResponse(s: String) {
                var a = "{\n" +
                        "    \"code\": 0,\n" +
                        "    \"msg\": \"success\",\n" +
                        "    \"data\": [\n" +
                        "        {\n" +
                        "            \"id\": \"AE87B356-7091-46CB-AB5C-CA9E22D19E89\",\n" +
                        "            \"orderNo\": \"AB202308100001\",\n" +
                        "            \"payUserName\": \"李四\",\n" +
                        "            \"collectionQueueId\": \"2d3e43a6-e5d5-479a-861f-efcd1ece5779\",\n" +
                        "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                        "            \"state\": 0,\n" +
                        "            \"isEnable\": true,\n" +
                        "            \"commission\": 123.00,\n" +
                        "            \"score\": 3000.00,\n" +
                        "            \"remark\": \"\",\n" +
                        "            \"created\": \"2023-08-10T23:20:34.837\",\n" +
                        "            \"userName\": \"张三\",\n" +
                        "            \"cardNo\": \"8888899999\",\n" +
                        "            \"bankName\": \"中国工商银行\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"count\": 0\n" +
                        "}\n"

                viewModelScope.launch {
//                    if (!s.isEmpty()){
//
//
//
//                    }
                    var data = Gson().fromJson(a,SellListData::class.java)
                    if (data!=null){
                        mSellListData.value = data
                    }
                }

            }

        })

        return mSellListData

    }

    fun getComfirmOrder(id : String , userName : String,context: Context) :LiveData<ConfirmData>{
        sellDateModel.setConfirmOrder(id,userName,context, object :SellDateModel.SellResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {

                    if (!s.isEmpty()){
                        var data = Gson().fromJson(s,ConfirmData::class.java)
                        if (data!=null){
                        }
                    }
                }
            }

        })


        return  confirmData

    }

}