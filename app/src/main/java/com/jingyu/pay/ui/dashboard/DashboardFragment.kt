package com.jingyu.pay.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.R
import com.jingyu.pay.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var  switch: Switch

    val sellViewModel: SellViewModel by lazy {
        ViewModelProvider(this, SellViewModelFactory()).get(SellViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        switch =  root.findViewById(R.id.switch1);

        switch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            val ischeckString = if (b) "卖币接单中" else "卖币暂停接单"
            switch.setText(ischeckString)
            openSell()
        })


        return root
    }

    fun openSell(){
        sellViewModel.setSellSetting(requireActivity()).observe(requireActivity(), Observer {
            Log.d("Jack","開啟"+ it.msg)


        })
    }

    fun closeSell(){
        sellViewModel.setCloseSellSetting(requireActivity()).observe(requireActivity(), Observer {
            Log.d("Jack","關閉"+ it.msg)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        closeSell()

    }
}