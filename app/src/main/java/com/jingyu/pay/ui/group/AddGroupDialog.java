package com.jingyu.pay.ui.group;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.jingyu.pay.PayHelperUtils;
import com.jingyu.pay.R;
import com.jingyu.pay.ui.home.StartBuyData;


public class AddGroupDialog extends AlertDialog {
    private Activity activity;
    private EditText pd, name,tel;
    private Spinner spinner;
    private OnAddCallback onAddCallback;
    private OnAddBanKListCallback onAddBanKListCallback;
    private Dialog dialog;
    private Switch aSwitch;
    private  TextView textView,textView2;
    private Handler handlerLoading = new Handler();
    GroupDateModel groupDateModel = new GroupDateModel();
    public void setOnAddCallback(OnAddCallback onAddCallback) {
        this.onAddCallback = onAddCallback;
    }

    public  void  setAddBankCallback(OnAddBanKListCallback onAddBanKListCallback){
        this.onAddBanKListCallback = onAddBanKListCallback;

    }

    public interface OnAddCallback {
        void onAdd(String cardNo, String bankName, String bankCode);
    }
    public  interface  OnAddBanKListCallback{
        void onResponse(RegisterData buyData);
    }

    public AddGroupDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }


    protected AddGroupDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected AddGroupDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_add_group, null);
        setView(view);
        setContentView(view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

        name = findViewById(R.id.bank_card);
        pd = findViewById(R.id.bank_card_no);
        textView = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        tel = findViewById(R.id.teledt);
        String maxString = PayHelperUtils.getRebate(activity).isEmpty() ? "" : PayHelperUtils.getRebate(activity);
        String minString = PayHelperUtils.getPaymentXeRebate(activity).isEmpty() ? "" : PayHelperUtils.getPaymentXeRebate(activity);





        TextView message = findViewById(R.id.message);
        message.setText("您的佣金比例:\n"+"卖币:"+maxString+"\n"+"小额买币:"+minString);
        textView.setText(maxString);
        textView2.setText(minString);


        view.findViewById(R.id.closeBtn).setOnClickListener(v -> {
            view.setEnabled(false);
            new Handler().postDelayed(() -> view.setEnabled(true), 500);

            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            dismiss();
        });

        view.findViewById(R.id.okBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String p = pd.getText().toString();
                String t = tel.getText().toString();
                groupDateModel.getGroupRegister(activity, n, p, t,
                        Double.parseDouble(PayHelperUtils.getRebate(activity)),
                        Double.parseDouble(PayHelperUtils.getPaymentXeRebate(activity)),
                        new GroupDateModel.GroupResponse() {
                            @Override
                            public void getResponse(@NonNull String s) {
                                Log.d("add",s);
                                if (!s.isEmpty()){
                                    RegisterData registerData = new Gson().fromJson(s,RegisterData.class);
                                    if (registerData!=null){
                                        onAddBanKListCallback.onResponse(registerData);
                                    }
                                }
                            }
                        });

                dismiss();

            }
        });
    }

}
