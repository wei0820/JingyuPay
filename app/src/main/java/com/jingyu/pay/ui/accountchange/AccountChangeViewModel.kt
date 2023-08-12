package com.jingyu.pay.ui.accountchange

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jingyu.pay.ui.login.LoginData
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
                    var accountChange = Gson().fromJson(s, AccountChange::class.java)
                    accountChangeData.value = accountChange
                }
            }

        })
        return  accountChangeData




    }

}