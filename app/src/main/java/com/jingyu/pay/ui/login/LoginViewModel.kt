package com.jingyu.pay.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    var homeViewModel = LoginDateModel()
    var  token  = MutableLiveData<LoginData>()

    fun getUserToken(loginid:String,password:String,code:String) : LiveData<LoginData>{
        homeViewModel.setUserLogin(loginid,password,code, object : LoginDateModel.LoginrResponse {
            override fun getResponse(s: String) {
                Log.d("Jack",s)
                viewModelScope.launch {
                    var userData = Gson().fromJson(s, LoginData::class.java)
                    token.value = userData

                }
            }

        })

        return  token;
    }



}