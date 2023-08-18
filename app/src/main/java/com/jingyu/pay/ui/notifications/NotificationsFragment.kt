package com.jingyu.pay.ui.notifications

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.MainActivity
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.R
import com.jingyu.pay.databinding.FragmentNotificationsBinding
import com.jingyu.pay.ui.accountchange.AccountChangeActivity
import com.jingyu.pay.ui.buyrecord.BuyRecordActivity
import com.jingyu.pay.ui.sellrecord.SellRecordActivity
import com.jingyu.pay.ui.transaction.TransactionActivity
import java.net.Inet4Address

class NotificationsFragment : Fragment() ,View.OnClickListener{

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var  layout_bankcard : LinearLayout

    lateinit var  buy_record_layout : RelativeLayout
    lateinit var  sell_record_layout : RelativeLayout
    lateinit var  frozenrecord : RelativeLayout
    lateinit var  account_layou : RelativeLayout



    val personalViewModel: PersonalViewModel by lazy {
        ViewModelProvider(this, PersonalViewModelFactory()).get(PersonalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        personalViewModel.get(requireActivity()).observe(requireActivity(), Observer {
            Log.d("Jack",it.data.note)
            Log.d("Jack",it.data.apIs)
            var array = it.data.apIs.split("|");
            Log.d("Jack",array.size.toString())
            Log.d("Jack",array.get(0).toString())
            Log.d("Jack",array.get(1).toString())
            PayHelperUtils.saveOpenUrl(context,array.get(0).toString())

            PayHelperUtils.isShowNews(context,it.data.note)



        })

         layout_bankcard  = root.findViewById(R.id.layout_banckcard)

        buy_record_layout  = root.findViewById(R.id.buy_record_layout)
        sell_record_layout  = root.findViewById(R.id.sell_record_layout)
        frozenrecord  = root.findViewById(R.id.frozenrecord)
        account_layou  = root.findViewById(R.id.account_layou)


        layout_bankcard.setOnClickListener {


            val intent  = Intent()
            intent.setClass(requireActivity(), AccountChangeActivity::class.java)
            startActivity(intent)
        }

        buy_record_layout.setOnClickListener(this)
        sell_record_layout.setOnClickListener(this)
        frozenrecord.setOnClickListener(this)
        account_layou.setOnClickListener(this)



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.layout_banckcard -> startActivity(Intent().setClass(requireActivity(),AccountChangeActivity::class.java))
            R.id.buy_record_layout -> startActivity(Intent().setClass(requireActivity(),BuyRecordActivity::class.java))
            R.id.sell_record_layout ->startActivity(Intent().setClass(requireActivity(),SellRecordActivity::class.java))
            R.id.frozenrecord ->startActivity(Intent().setClass(requireActivity(),TransactionActivity::class.java))
            R.id.account_layou -> startActivity(Intent().setClass(requireActivity(),AccountChangeActivity::class.java))




        }
    }


}