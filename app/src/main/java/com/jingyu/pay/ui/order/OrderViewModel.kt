package com.jingyu.pay.ui.order

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

class OrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var  orderDateModel = OrderDateModel()
    var  paymentMatchingData = MutableLiveData<PaymentMatchingData>()


    fun getPaymentMatching(context: Context) : LiveData<PaymentMatchingData>{
        orderDateModel.getPaymentMatching(context, object : OrderDateModel.OrderResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var a = "{\n" +
                                "    \"code\": 0,\n" +
                                "    \"msg\": \"success\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": \"E24165EE-3ED0-4E79-9BB3-2A4629696595\",\n" +
                                "            \"bankName\": \"中国工商银行\",\n" +
                                "            \"cardId\": \"6212261609000030897\",\n" +
                                "            \"subName\": \"中国工商银行\",\n" +
                                "            \"userName\": \"杨雪芹\",\n" +
                                "            \"score\": 2000,\n" +
                                "            \"orderNo\": \"DW7HFG79249085781222\",\n" +
                                "            \"paymentOrderId\": \"E4B477A6-DFB9-49C6-9B47-FD24B74AC539\",\n" +
                                "            \"state\": 0,\n" +
                                "            \"isEnable\": true,\n" +
                                "            \"created\": \"2023-08-01T17:53:03.66\",\n" +
                                "            \"remark\": \"\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"count\": 0\n" +
                                "}\n"
                        var data = Gson().fromJson(a,PaymentMatchingData::class.java);
                        if (data!=null){
                            paymentMatchingData.value = data
                        }
                    }
                }
            }

            override fun getFailure(s: String) {
            }

        })
        return paymentMatchingData;
    }

}
