package com.jingyu.pay;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    // 存api
    public static void saveChangeAPI(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.CALL_API, Context.MODE_PRIVATE).edit();
        edit.putString(Constant.CALL_API, token).apply();
    }

    public static String getChangeAPI(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.CALL_API, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constant.CALL_API, "");
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
        if (!localNews.equals(getNews)){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("公告");
            alertDialog.setMessage(getNews);
            /*一樣，不熟的用這個打就OK了*/
            alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveUserInfoNews(context,getNews);
                }
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

    }


    public static String md5(String content) {
        byte[] hash;
        String newString = "2io#ejQO" +  content;
        try {
            hash = MessageDigest.getInstance("MD5").digest(newString.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException",e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    // 存公告
    public static void saveBuyMax(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.BUY_MAX, Context.MODE_PRIVATE).edit();
        edit.putString(Constant.BUY_MAX, token).apply();
    }

    public static String getBuyMax(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.BUY_MAX, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constant.BUY_MAX, "");
    }

    public static void saveBuyMin(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.BUY_MIN, Context.MODE_PRIVATE).edit();
        edit.putString(Constant.BUY_MIN, token).apply();
    }

    public static String getBuyMin(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.BUY_MIN, Context.MODE_PRIVATE);

        return sharedPreferences.getString(Constant.BUY_MIN, "");
    }


    public static void saveBuyIsOpen(Context context, boolean token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(Constant.BUY_ISOPEN, Context.MODE_PRIVATE).edit();
        edit.putBoolean(Constant.BUY_ISOPEN, token).apply();
    }

    public static Boolean getBuyIsOpen(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.BUY_ISOPEN, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(Constant.BUY_ISOPEN, true);
    }

}
