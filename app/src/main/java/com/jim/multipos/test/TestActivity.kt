package com.jim.multipos.test

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jim.multipos.R
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.provideViewHolder
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class TestActivity: AppCompatActivity() {

    private lateinit var recyclerView: MPRecyclerView<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_layout)
        recyclerView = findViewById(R.id.rvTest)
        recyclerView.viewHolder = provideViewHolder<String, TestViewHolder>(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val list = mutableListOf<String>()
        for (i in 0..100) {
            list.add("Position $i")
        }
        recyclerView.setItems(list)
        recyclerView.listener = object: MPRecyclerView.OnLoadMoreListener {
            override fun onRefresh(recyclerView: RecyclerView) {
                refresh()
            }

            override fun onLoadMore(recyclerView: RecyclerView) {
                loadMore()
            }
        }
    }

    fun refresh() {
        val a = Observable
                .timer(10, TimeUnit.SECONDS)
                .subscribe {
                    recyclerView.refreshComplete()
                }
    }

    fun loadMore() {
        val a = Observable
                .timer(2, TimeUnit.SECONDS)
                .subscribe({
                    val list = mutableListOf<String>()
                    for (i in 0..100) {
                        list.add("Position asdfas")
                    }
                    recyclerView.addItems(list)
                    recyclerView.loadMoreComplete()
                }, {

                })
    }

}

class TestViewHolder(itemView: View): BaseViewHolder<String>(itemView) {

    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<String> {
        val view = LayoutInflater.from(context).inflate(R.layout.test_adapter, parent, false)
        return TestViewHolder(view)
    }

    val tvTest: TextView? = itemView.findViewById(R.id.tvTest)
    override fun onBind(item: String?, position: Int, isSelected: Boolean) {
        tvTest?.text = item ?: "Progress $position"
    }
}

//class ProdTestViewHolder(itemView: View): BaseViewHolder<ProductClass>(itemView) {
//    override fun newInstance(context: Context): BaseViewHolder<ProductClass> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    val tvTest: TextView? = itemView.findViewById(R.id.tvTest)
//    override fun onBind(item: ProductClass?, position: Int, isSelected: Boolean) {
//        Log.d("sss", "Pos: $position")
//        tvTest?.text = item?.name ?: "Progress $position"
//    }
//}