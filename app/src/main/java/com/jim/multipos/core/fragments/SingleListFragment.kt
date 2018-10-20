package com.jim.multipos.core.fragments

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.jcodecraeer.xrecyclerview.SimpleViewSwitcher
import com.jim.multipos.R
import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.customView.recyclerView.adapter.BaseSelectableAdapter
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.adapter.SelectableAdapter
import kotlinx.android.synthetic.main.single_list_fragment.*
import java.io.Serializable

abstract class SingleListFragment<X: Serializable, VH: BaseViewHolder<X>, Y: BaseSelectableAdapter<X>, T: ViewDataBinding, V: SingleListViewModel<X>>: BaseFragment<T, V>() {

    override fun getLayoutId(): Int = R.layout.single_list_fragment

    internal lateinit var adapter: Y

    internal var empty: Boolean = false
        set(value) {
            if (value) {
                tvEmpty.visibility = View.VISIBLE
                ivEmpty.visibility = View.VISIBLE
                rvSingle.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                rvSingle.visibility = View.VISIBLE
            }

            field = value
        }

    internal var loading: Boolean = false
        set(value) {
            if (value) {
                tvEmpty.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                pbProgress.visibility = View.VISIBLE
            } else {
                tvEmpty.visibility = View.VISIBLE
                ivEmpty.visibility = View.VISIBLE
                pbProgress.visibility = View.GONE
            }
            field = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initRV()
        if (buttonClickDefaultAction()) {
            fab.setOnClickListener {
                this.buttonAction()
            }
        }
        tvEmpty.text = emptyText()

        mViewModel?.isLoading?.observe(this, Observer {
            loading = it ?: false
        })

        val header = CustomRefreshHeader(context!!)
        header.setArrowImageView(R.drawable.ic_iconmonstr_arrow)
    }

    internal abstract fun initRV()

    internal abstract fun buttonAction()
    internal abstract fun emptyText(): String

    internal open fun buttonClickDefaultAction() = true

}
