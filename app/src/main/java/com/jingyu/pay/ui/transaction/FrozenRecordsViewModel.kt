package com.jingyu.pay.ui.transaction

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

class FrozenRecordsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var  orderDateModel = FrozenRecordsDateModel()
    var  paymentMatchingData = MutableLiveData<FrozenRecordsData>()


    fun getSellRecodeList(context: Context,date: String) : LiveData<FrozenRecordsData>{
        orderDateModel.getFrozenRecordsData(context,date, object : FrozenRecordsDateModel.OrderResponse {
            override fun getResponse(s: String) {
                if (!s.isEmpty()){
                    viewModelScope.launch {
                        var a = "{\n" +
                                "    \"code\": 0,\n" +
                                "    \"msg\": \"success\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": \"8D3EB4B9-27D3-4D38-87E6-C1450393EA87\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"score\": 2000.00,\n" +
                                "            \"sourceId\": \"BE562CE1-0142-4FEE-A667-967F2E8580A3\",\n" +
                                "            \"type\": \"C\",\n" +
                                "            \"tag\": \"A\",\n" +
                                "            \"resoult\": \"卖币用户确认\",\n" +
                                "            \"isEnable\": false,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"created\": \"2023-08-10T23:31:53.86\",\n" +
                                "            \"updated\": \"2023-08-10 23:46:50\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"B6D5CD88-36FC-4EE9-9319-98FB8FF7DF2A\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"score\": 3000.00,\n" +
                                "            \"sourceId\": \"AE87B356-7091-46CB-AB5C-CA9E22D19E89\",\n" +
                                "            \"type\": \"C\",\n" +
                                "            \"tag\": \"A\",\n" +
                                "            \"resoult\": \"卖币用户确认\",\n" +
                                "            \"isEnable\": false,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"created\": \"2023-08-10T23:20:34.837\",\n" +
                                "            \"updated\": \"2023-08-10 23:48:26\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"5E682FEF-F566-4648-9413-CF871C96C2AC\",\n" +
                                "            \"accountId\": \"2b97224a-d83c-4d9a-b24a-cdea8e07f7fc\",\n" +
                                "            \"score\": 2000.00,\n" +
                                "            \"sourceId\": \"27870D8E-1CA1-430F-9FFE-044738D5F058\",\n" +
                                "            \"type\": \"C\",\n" +
                                "            \"tag\": \"A\",\n" +
                                "            \"resoult\": \"\",\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"created\": \"2023-08-10T23:24:40.183\",\n" +
                                "            \"updated\": \"\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"count\": 0\n" +
                                "}\n"
                        var data = Gson().fromJson(a,FrozenRecordsData::class.java)
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
