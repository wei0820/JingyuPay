package com.jingyu.pay.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jingyu.pay.PayHelperUtils
import com.jingyu.pay.databinding.FragmentNotificationsBinding
import com.jingyu.pay.ui.home.HomeViewModel
import com.jingyu.pay.ui.home.HomeViewModelFactory

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

            PayHelperUtils.isShowNews(context,it.data.note)



        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}