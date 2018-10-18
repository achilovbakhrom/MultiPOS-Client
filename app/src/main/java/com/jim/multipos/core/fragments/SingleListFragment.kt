package com.jim.multipos.core.fragments

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.adapter.BaseViewHolder
import com.jim.multipos.core.adapter.SelectableAdapter
import kotlinx.android.synthetic.main.single_list_fragment.*
import java.io.Serializable

abstract class SingleListFragment<X: Serializable, VH: BaseViewHolder<X>, Y: SelectableAdapter<X, VH>,T: ViewDataBinding, V: BaseViewModel>: BaseFragment<T, V>() {

    override fun getLayoutId(): Int = R.layout.single_list_fragment

    internal lateinit var adapter: Y

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initRV()
        this.initObservers()
        if (buttonClickDefaultAction()) {
            fab.setOnClickListener {
                this.buttonAction()
            }
        }
        tvEmpty.text = emptyText()
    }

    internal abstract fun initRV()
    internal abstract fun initObservers()
    internal abstract fun buttonAction()
    internal abstract fun emptyText(): String

    internal open fun buttonClickDefaultAction() = true
}