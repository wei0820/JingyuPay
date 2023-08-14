package com.jingyu.pay.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.AddBankCardDialog
import com.jingyu.pay.R
import com.jingyu.pay.databinding.FragmentHomeBinding
import com.jingyu.pay.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var addbutton : Button? = null

    val merchantOrdersViewModel: OrderViewModel by lazy {
        ViewModelProvider(this, OrderViewModelFactory()).get(OrderViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        merchantOrdersViewModel.getPaymentMatching(requireActivity()).observe(requireActivity(),
            Observer {
                Log.d("Jack",it.msg);
            })






        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}