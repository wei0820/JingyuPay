package com.jingyu.pay.ui.order

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
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
                        var data = Gson().fromJson(s,PaymentMatchingData::class.java);
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