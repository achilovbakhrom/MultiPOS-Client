package com.jim.multipos.core.fragments

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.BaseViewModel
import kotlinx.android.synthetic.main.single_list_fragment.*

abstract class SingleListFragment<T: ViewDataBinding, V: BaseViewModel>: BaseFragment<T, V>() {

    override fun getLayoutId(): Int = R.layout.single_list_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initRV()
        this.initObservers()
        if (buttonClickDefaultAction()) {
            fab.setOnClickListener {
                this.buttonAction()
            }
        }
    }

    internal abstract fun initRV()
    internal abstract fun initObservers()
    internal abstract fun buttonAction()

    internal open fun buttonClickDefaultAction() = true
}