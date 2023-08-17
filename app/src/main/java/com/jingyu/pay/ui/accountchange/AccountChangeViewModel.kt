package com.jingyu.pay.ui.accountchange

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AccountChangeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var accountChangeDateModel = AccountChangeDateModel()

    var  accountChangeData = MutableLiveData<AccountChange>()


    fun  getAccountChangeData(context: Context) : LiveData<AccountChange>{

        accountChangeDateModel.getAccountChangeDate(context, object : AccountChangeDateModel.AccountCahngeResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var a = "{\n" +
                                "    \"code\": 0,\n" +
                                "    \"msg\": \"success\",\n" +
                                "    \"data\": [\n" +
                                "        {\n" +
                                "            \"id\": \"4C7FA8B5-E6E8-4DE7-9679-88EC015D8021\",\n" +
                                "            \"loginId\": \"ceshi\",\n" +
                                "            \"score\": -3000.00,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"quota\": 20202.00,\n" +
                                "            \"quotaEnd\": 17202.00,\n" +
                                "            \"created\": \"2023-08-10T23:20:34.84\",\n" +
                                "            \"sourceId\": \"AE87B356-7091-46CB-AB5C-CA9E22D19E89\",\n" +
                                "            \"tag\": \"C\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"497A90BA-F3BE-4DFF-BC5C-E3D0DF22930F\",\n" +
                                "            \"loginId\": \"ceshi\",\n" +
                                "            \"score\": -2000.00,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"quota\": 17202.00,\n" +
                                "            \"quotaEnd\": 15202.00,\n" +
                                "            \"created\": \"2023-08-10T23:24:40.187\",\n" +
                                "            \"sourceId\": \"27870D8E-1CA1-430F-9FFE-044738D5F058\",\n" +
                                "            \"tag\": \"C\"\n" +
                                "        },\n" +
                                "        {\n" +
                                "            \"id\": \"FACD5EC5-A10D-40AF-80C7-E7A7315224DA\",\n" +
                                "            \"loginId\": \"ceshi\",\n" +
                                "            \"score\": -2000.00,\n" +
                                "            \"remark\": \"卖币交易\",\n" +
                                "            \"quota\": 15202.00,\n" +
                                "            \"quotaEnd\": 13202.00,\n" +
                                "            \"created\": \"2023-08-10T23:31:53.883\",\n" +
                                "            \"sourceId\": \"BE562CE1-0142-4FEE-A667-967F2E8580A3\",\n" +
                                "            \"tag\": \"C\"\n" +
                                "        }\n" +
                                "    ],\n" +
                                "    \"count\": 3\n" +
                                "}\n"
                        var data = Gson().fromJson(a, AccountChange::class.java)

                        if (data!=null){
                            accountChangeData.value = data

                        }
                    }

                }
            }

        })
        return  accountChangeData




    }

}