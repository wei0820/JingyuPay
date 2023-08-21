package com.jingyu.pay.ui.group

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jingyu.pay.R
import com.jingyu.pay.ToastManager


class GroupListctivity : AppCompatActivity() {
    lateinit var fab: FloatingActionButton
    lateinit  var recyclerView: RecyclerView
    var adapter: Adapter? = null
    var buyDataList: ArrayList<GroupListData.Data> = ArrayList()
    val groupViewModel: GroupViewModel by lazy {
        ViewModelProvider(this, GroupViewModelFactory()).get(GroupViewModel::class.java)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_listctivity)
        title = "团队成员"
         fab  = findViewById(R.id.normalFAB)
         recyclerView =  findViewById(R.id.recyclerView)
        getGroupList()
        adapter = Adapter()

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        adapter!!.updateList(buyDataList)

        recyclerView!!.adapter = adapter

        adapter!!.notifyDataSetChanged()

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

        fab.setOnClickListener {
            val dialog = AddGroupDialog(this)
            dialog.setAddBankCallback {
                if (it!=null){
                    runOnUiThread {
                        ToastManager.showToastCenter(this,it.msg)
                        getGroupList()

                    }
                }
            }
            dialog.show()
        }


    }

    fun  getGroupList(){
        groupViewModel.getGroupList(this).observe(this, Observer {
            buyDataList.clear()
            if (it.code == 0){
                if (it.data!=null){
                    for (datum in it.data) {
                        buyDataList.add(datum)

                        adapter!!.notifyDataSetChanged()
                    }
                    if (buyDataList.size<=0){
                        adapter!!.notifyDataSetChanged()

                    }
                }
            }
        })
    }


}
class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {
    var bankCardInfoList:ArrayList<GroupListData.Data>? = null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bankName: TextView
        var cardNo: TextView
        var time: TextView
        var amount: TextView
        var orderno: TextView
        var frozen : TextView


        init {
            bankName = view.findViewById(R.id.bankname)
            cardNo = view.findViewById(R.id.cardno)
            time = view.findViewById(R.id.time)
            amount = view.findViewById(R.id.amount)
            orderno = view.findViewById(R.id.orderno)
            frozen = view.findViewById(R.id.frozen);


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.group_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info: GroupListData.Data = bankCardInfoList!!.get(position)
        holder.orderno.text = "用户名:" +info.loginId
        holder.bankName.text = "电话:"+info.mobile
        holder.time.text = "创建日期:"+info.created

        holder.cardNo.text ="可用余额￥"+ info.quota

        holder.amount.text = "佣金￥"+info.commission

        holder.frozen.text = "进行中的冻结金额￥" + info.frozen


    }

    override fun getItemCount(): Int {
        return bankCardInfoList!!.size
    }

    //更新資料用
    fun updateList(list:ArrayList<GroupListData.Data>){
        bankCardInfoList = list
    }
}
