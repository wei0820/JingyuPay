package com.jingyu.pay.ui.sellrecord

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.R
import com.jingyu.pay.ui.dashboard.DashboardFragment
import com.jingyu.pay.ui.dashboard.SellListData
import kotlinx.coroutines.launch

class SellRecodeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var  orderDateModel = SellRecordDateModel()
    var  paymentMatchingData = MutableLiveData<SellRecordData>()


    fun getSellRecodeList(context: Context,date: String) : LiveData<SellRecordData>{
        orderDateModel.getSellRecordData(context,date, object : SellRecordDateModel.OrderResponse {
            override fun getResponse(s: String) {
                if (!s.isEmpty()){
                    viewModelScope.launch {

                        var a = "{\n" +
                                "    \"code\": 0,\n" +
                                "    \"msg\": \"success\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": \"BE562CE1-0142-4FEE-A667-967F2E8580A3\",\n" +
                                "            \"orderNo\": \"AB202308100003\",\n" +
                                "            \"isError\": false,\n" +
                                "            \"userName\": \"黃安\",\n" +
                                "            \"collectionQueueId\": \"2d3e43a6-e5d5-479a-861f-efcd1ece5779\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"state\": 2,\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"commission\": 30.000000,\n" +
                                "            \"score\": 2000.00,\n" +
                                "            \"remark\": \"\",\n" +
                                "            \"created\": \"2023-08-10T23:31:53.86\",\n" +
                                "            \"cBankName\": \"中国工商银行\",\n" +
                                "            \"cUserName\": \"张三\",\n" +
                                "            \"cardNo\": \"8888899999\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"27870D8E-1CA1-430F-9FFE-044738D5F058\",\n" +
                                "            \"orderNo\": \"AB202308100002\",\n" +
                                "            \"isError\": false,\n" +
                                "            \"userName\": \"王五\",\n" +
                                "            \"collectionQueueId\": \"2d3e43a6-e5d5-479a-861f-efcd1ece5779\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"state\": 0,\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"commission\": 30.000000,\n" +
                                "            \"score\": 2000.00,\n" +
                                "            \"remark\": \"\",\n" +
                                "            \"created\": \"2023-08-10T23:24:40.183\",\n" +
                                "            \"cBankName\": \"中国工商银行\",\n" +
                                "            \"cUserName\": \"张三\",\n" +
                                "            \"cardNo\": \"8888899999\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"AE87B356-7091-46CB-AB5C-CA9E22D19E89\",\n" +
                                "            \"orderNo\": \"AB202308100001\",\n" +
                                "            \"isError\": false,\n" +
                                "            \"userName\": \"張三\",\n" +
                                "            \"collectionQueueId\": \"2d3e43a6-e5d5-479a-861f-efcd1ece5779\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"state\": 2,\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"commission\": 45.000000,\n" +
                                "            \"score\": 3000.00,\n" +
                                "            \"remark\": \"\",\n" +
                                "            \"created\": \"2023-08-10T23:20:34.837\",\n" +
                                "            \"cBankName\": \"中国工商银行\",\n" +
                                "            \"cUserName\": \"张三\",\n" +
                                "            \"cardNo\": \"8888899999\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"count\": 0\n" +
                                "}\n"
                        var data = Gson().fromJson(a,SellRecordData::class.java)
                        if (data != null){

                            paymentMatchingData.value = data
                        }
                    }

                }
            }

            override fun getFailure(s: String) {
            }

        })
        return paymentMatchingData
    }

}
