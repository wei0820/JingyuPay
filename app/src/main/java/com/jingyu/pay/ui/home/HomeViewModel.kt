package com.jingyu.pay.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var homeViewModel = HomeDateModel()
    var  startBuy = MutableLiveData<StartBuyData>()




        fun getBuySetting(context: Context) : LiveData<StartBuyData> {
            homeViewModel.setBuySetting(context, object : HomeDateModel.BuyResponse {
                override fun getResponse(s: String) {
                    viewModelScope.launch {
                        if (!s.isEmpty()) {
                            var data = Gson().fromJson(s, StartBuyData::class.java)
                            Log.d("Jack", data.msg)
                            Log.d("Jack", data.data.toString())
                            startBuy.value = data

                        }
                    }
                }

                override fun getFailure(s: String) {
                }

            })
            return  startBuy


        }
}