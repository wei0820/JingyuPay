package com.jingyu.pay;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;



import java.io.IOException;
import java.util.List;



public class AddBankCardDialog extends AlertDialog {
    private Activity activity;
    private EditText bankCardNoEditText;
    private Spinner spinner;
    private OnAddCallback onAddCallback;
//    private OnAddBanKListCallback onAddBanKListCallback;
    private Dialog dialog;

    private Handler handlerLoading = new Handler();

    public void setOnAddCallback(OnAddCallback onAddCallback) {
        this.onAddCallback = onAddCallback;
    }

//    public  void  setAddBankCallback(OnAddBanKListCallback onAddBanKListCallback){
//        this.onAddBanKListCallback = onAddBanKListCallback;
//
//    }

    public interface OnAddCallback {
        void onAdd(String cardNo, String bankName, String bankCode);
    }
//    public  interface  OnAddBanKListCallback{
//        void onResponse(UpdateOnlineData addBankData);
//    }

    public AddBankCardDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }


    protected AddBankCardDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected AddBankCardDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_bank_card, null);
        setView(view);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        bankCardNoEditText = findViewById(R.id.bank_card_no);

        TextView message = findViewById(R.id.message);
//        message.setText(Constant.getEnvironmentInfo().getMessage());


        view.findViewById(R.id.closeBtn).setOnClickListener(v -> {
            view.setEnabled(false);
            new Handler().postDelayed(() -> view.setEnabled(true), 500);

            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            dismiss();
        });

        view.findViewById(R.id.okBtn);
    }


}
