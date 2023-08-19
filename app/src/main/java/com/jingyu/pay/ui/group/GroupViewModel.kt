package com.jingyu.pay.ui.group

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    var groupDateModel = GroupDateModel()
    var groupListData = MutableLiveData<GroupListData>()



    fun getGroupList(context: Context) : LiveData<GroupListData>{
        groupDateModel.getGroupTimeList(context, object : GroupDateModel.GroupResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if(!s.isEmpty()){
                        var data = Gson().fromJson(s,GroupListData::class.java)
                        if (data !=null){
                            groupListData.value = data
                        }
                    }
                }


            }

        })
        return groupListData;
    }

}