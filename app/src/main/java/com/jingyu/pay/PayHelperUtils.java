package com.jingyu.pay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

public class PayHelperUtils {
    // 存token
    public static void saveUserLoginToken(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.LOGIN_USER_TOKEN, Context.MODE_PRIVATE).edit();
        edit.putString(Constant.LOGIN_USER_TOKEN, token).apply();
    }

    public static String getUserToken(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.LOGIN_USER_TOKEN, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constant.LOGIN_USER_TOKEN, "");
    }
    // 存公告
    public static void saveUserInfoNews(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.USERINFO_NEWS, Context.MODE_PRIVATE).edit();
        edit.putString(Constant.USERINFO_NEWS, token).apply();
    }

    public static String getUserInfoNews(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.USERINFO_NEWS, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constant.USERINFO_NEWS, "");
    }


    public static void isShowNews(Context context,String getNews){
        String localNews = getUserInfoNews(context);
        if (localNews.equals(getNews)){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("公告");
            alertDialog.setMessage(getNews);
            /*一樣，不熟的用這個打就OK了*/
            alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            alertDialog.setNeutralButton("取消",(dialog, which) -> {
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

    }


}
