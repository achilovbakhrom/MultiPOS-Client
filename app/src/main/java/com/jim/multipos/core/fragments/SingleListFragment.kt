package com.jim.multipos.core.fragments

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.SingleListViewModel
import kotlinx.android.synthetic.main.single_list_fragment.*
import java.io.Serializable

abstract class SingleListFragment<X: Serializable, T: ViewDataBinding, V: SingleListViewModel<X>>: BaseFragment<T, V>() {

    override fun getLayoutId(): Int = R.layout.single_list_fragment

    internal var empty: Boolean = false
        set(value) {
            if (value) {
                tvEmpty.visibility = View.VISIBLE
                ivEmpty.visibility = View.VISIBLE
                pbProgress.visibility = View.GONE
                rvSingle.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                rvSingle.visibility = View.VISIBLE
                pbProgress.visibility = View.GONE
            }

            field = value
        }

    internal var isLoading: Boolean = false
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
            isLoading = it ?: false
        })

    }

    internal abstract fun initRV()

    internal abstract fun buttonAction()
    internal abstract fun emptyText(): String

    internal open fun buttonClickDefaultAction() = true

}
