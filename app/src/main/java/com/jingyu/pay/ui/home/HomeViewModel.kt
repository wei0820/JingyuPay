package com.jingyu.pay.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var homeViewModel = HomeDateModel()
    var  string = MutableLiveData<String>()


    fun  get() : LiveData<String>{
        Log.d("Jack","get");

        homeViewModel.test("", object : HomeDateModel.OrderResponse {
            override fun getResponse(s: String) {
                Log.d("Jack",s);
                viewModelScope.launch {
                    string.value = s;
                }
            }

            override fun getFailure(s: String) {
            }

        })
        return  string;
    }

}