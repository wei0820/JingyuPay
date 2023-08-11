package com.jingyu.pay.ui.accountchange

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

class AccountChangeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var addbutton : Button? = null

    val merchantOrdersViewModel: AccountChangeViewModel by lazy {
        ViewModelProvider(this, AccountChangeViewModelFactory()).get(AccountChangeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        addbutton = root.findViewById(R.id.addbtn)
        addbutton!!.setOnClickListener {
            val dialog = AddBankCardDialog(activity)
            dialog.show()

        }






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