package com.jingyu.pay.ui.group

import android.content.Context
import android.util.Log
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
    var groupRepostData = MutableLiveData<ReportsData>()
    var mReportsTeamData = MutableLiveData<ReportsTeamData>()
    var mReportDayData = MutableLiveData<ReportDayData>()



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

    fun getReport(context: Context, id : String,day : String) : LiveData<ReportsData>{

        groupDateModel.getGroupReportsList(context,id,day, object :GroupDateModel.GroupResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var data = Gson().fromJson(s,ReportsData::class.java)
                        if (data != null){
                            groupRepostData.value = data

                        }
                    }
                }
            }

        })


        return  groupRepostData
    }

    fun getReportTime(context: Context, id : String,day : String) : LiveData<ReportsTeamData>{

        groupDateModel.getGroupReportsTeamList(context,id,day, object :GroupDateModel.GroupResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    if (!s.isEmpty()){
                        var data = Gson().fromJson(s,ReportsTeamData::class.java)
                        if (data != null){
                            mReportsTeamData.value = data

                        }
                    }
                }
            }


        })
        return mReportsTeamData;
    }

    fun getGroupReportDay(context: Context):LiveData<ReportDayData>{

        groupDateModel.getGroupReportDay(context, object :GroupDateModel.GroupResponse {
            override fun getResponse(s: String) {
                viewModelScope.launch {
                    Log.d("report",s)
                    var a = "{\n" +
                            "    \"code\": 0,\n" +
                            "    \"msg\": \"success\",\n" +
                            "    \"data\": [\n" +
                            "        {\n" +
                            "            \"id\": \"B5613D2E-CA9C-48B9-8FC1-2CEDBDD6BFD1\",\n" +
                            "            \"accountId\": \"a9b2bd11-0715-41d3-ad87-3316e93d7548\",\n" +
                            "            \"score\": 19.47,\n" +
                            "            \"tag\": 3,\n" +
                            "            \"sourceId\": \"AA49CA85-0C8F-452F-B652-7FD58C6EE91A\",\n" +
                            "            \"created\": \"2023-07-27T23:28:24.723\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"95E5149C-D252-41F9-A117-5A93B0F11B0C\",\n" +
                            "            \"accountId\": \"a9b2bd11-0715-41d3-ad87-3316e93d7548\",\n" +
                            "            \"score\": 18.78,\n" +
                            "            \"tag\": 3,\n" +
                            "            \"sourceId\": \"D213E097-D48F-4548-B426-D23EA9792EA6\",\n" +
                            "            \"created\": \"2023-07-27T23:28:24.723\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "            \"id\": \"BD2888EA-AAFD-4C07-A4EE-1B6FA212CD3E\",\n" +
                            "            \"accountId\": \"a9b2bd11-0715-41d3-ad87-3316e93d7548\",\n" +
                            "            \"score\": 18.78,\n" +
                            "            \"tag\": 3,\n" +
                            "            \"sourceId\": \"CC847714-A35B-426C-8543-4B2E8BE7E680\",\n" +
                            "            \"created\": \"2023-07-27T23:26:03.06\"\n" +
                            "        }\n" +
                            "   ],\n" +
                            "    \"count\": 0\n" +
                            "}\n"
                    if (!s.isEmpty()){
                        var data = Gson().fromJson(a,ReportDayData::class.java)
                        if (data !=null){
                            mReportDayData.value = data
                        }
                    }
                }
            }

        })



        return mReportDayData;

    }

}