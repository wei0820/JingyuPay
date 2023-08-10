package com.jingyu.pay.ui.notifications;

import com.google.gson.annotations.SerializedName;

public class UserinfoData {
    @SerializedName("code") public  int code;
    @SerializedName("msg") public  String msg;
    @SerializedName("count") public  int count;
    @SerializedName("data") public  Data data;

    public class Data{
        @SerializedName("rebate") public  Double rebate;
        @SerializedName("paymentXeRebate") public  Double paymentXeRebate;
        @SerializedName("quota") public  Double quota;
        @SerializedName("frozen") public  Double frozen;
        @SerializedName("payment") public  Double payment;
        @SerializedName("collection") public  Double collection;
        @SerializedName("commission") public  Double commission;
        @SerializedName("isPayment") public  boolean isPayment;
        @SerializedName("isCollection") public  boolean isCollection;
        @SerializedName("isEnable") public  boolean isEnable;
        @SerializedName("isSecret") public  boolean isSecret;
        @SerializedName("isVIP") public  boolean isVIP;
        @SerializedName("cardQty") public  int cardQty;
        @SerializedName("note") public  String note;
        @SerializedName("apIs") public  String apIs;
        @SerializedName("index") public  int index;

    }



}
