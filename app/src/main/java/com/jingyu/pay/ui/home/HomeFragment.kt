package com.jingyu.pay.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jingyu.pay.AddBankCardDialog
import com.jingyu.pay.R
import com.jingyu.pay.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    var adapter: Adapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var addbutton : Button? = null

    val merchantOrdersViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val fab: FloatingActionButton = root.findViewById(R.id.normalFAB)
        val recyclerView: RecyclerView =  root.findViewById(R.id.recyclerView)

        var recyclerViewData = ArrayList<String>()
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")

        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")
        recyclerViewData.add("1")



        adapter = Adapter()

        // create a vertical layout manager
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        adapter!!.updateList(recyclerViewData)

        recyclerView!!.adapter = adapter

        adapter!!.notifyDataSetChanged()

        // make the adapter the data set changed for the recycler view

        // now creating the scroll listener for the recycler view
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // if the recycler view is scrolled
                // above hide the FAB
                if (dy > 10 && fab.isShown) {
                    fab.hide()
                }

                // if the recycler view is
                // scrolled above show the FAB
                if (dy < -10 && !fab.isShown) {
                    fab.show()
                }

                // of the recycler view is at the first
                // item always show the FAB
                if (!recyclerView.canScrollVertically(-1)) {
                    fab.show()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == 0){
                    fab.show()
                }

            }
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
    class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {
        var bankCardInfoList:ArrayList<String>? = null


        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var accountNo: TextView
            var accountInfo: TextView
            lateinit var orderbutton : Button


            init {
                accountNo = view.findViewById(R.id.accountNo)
                accountInfo = view.findViewById(R.id.accountInfo)
                orderbutton = view.findViewById(R.id.addbtn)


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.buy_list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val info = bankCardInfoList!!.get(position)
            holder.orderbutton.text = "處理"
            holder.accountNo.text  =info
            holder.accountInfo.text = ""





        }

        override fun getItemCount(): Int {
            return bankCardInfoList!!.size
        }

//          //更新資料用
//          fun updateList(list:List<String>){
//              bankCardInfoList = list
//          }

        //更新資料用
        fun updateList(list:ArrayList<String>){
            bankCardInfoList = list
        }
    }
}