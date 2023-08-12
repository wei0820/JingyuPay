package com.jingyu.pay.ui.accountchange

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.databinding.ActivityAccountBinding
import com.jingyu.pay.databinding.ActivityPurchaseHistoryBinding
import com.jingyu.pay.ui.notifications.PersonalViewModel
import com.jingyu.pay.ui.notifications.PersonalViewModelFactory

class AccountChangeActivity: AppCompatActivity()  {

    lateinit var  binding: ActivityAccountBinding;
    lateinit var  closebtn : ImageButton


    val accountChangeViewModel: AccountChangeViewModel by lazy {
        ViewModelProvider(this, AccountChangeViewModelFactory()).get(AccountChangeViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.closeBtn.setOnClickListener {
            this.finish();
        }




        accountChangeViewModel.getAccountChangeData(this).observe(this, Observer {
            for (data in it.data) {
                Log.d("AccountChangeActivity",data.id)

            }
        })

    }
}