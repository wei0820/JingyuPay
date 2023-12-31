package com.jingyu.pay.ui.sellrecord;

import com.google.gson.annotations.SerializedName;

public class SellRecordData {
    @SerializedName("code") public  int code;
    @SerializedName("msg") public  String msg;
    @SerializedName("data") public  Data[] data;

    public class Data{
//        @SerializedName("id") public String id;
//        @SerializedName("orderNo") public String orderNo;
//        @SerializedName("bankName") public String bankName;
//        @SerializedName("subName") public String subName;
//        @SerializedName("userName") public String userName;
//        @SerializedName("cardId") public String cardId;
//        @SerializedName("paymentOrderId") public String paymentOrderId;
//        @SerializedName("paymentQueueId") public String paymentQueueId;
//        @SerializedName("accountId") public String accountId;
//        @SerializedName("state") public Double state;
//        @SerializedName("isEnable") public  boolean isEnable;
//        @SerializedName("commission")public Double commission;
//        @SerializedName("score") public Double score;
//        @SerializedName("remark") public String remark;
//        @SerializedName("created") public String created;
//



        @SerializedName("orderNo") public String orderNo;
        @SerializedName("isError") public boolean isError;
        @SerializedName("userName") public String userName;
        @SerializedName("collectionQueueId") public String collectionQueueId;
        @SerializedName("accountId") public String accountId;
        @SerializedName("state") public int state;
        @SerializedName("isEnable") public boolean isEnable;
        @SerializedName("commission") public double commission;
        @SerializedName("score") public double score;
        @SerializedName("created") public String created;
        @SerializedName("cBankName") public String cBankName;
        @SerializedName("cUserName") public String cUserName;
        @SerializedName("cardNo") public String cardNo;


    }


}
