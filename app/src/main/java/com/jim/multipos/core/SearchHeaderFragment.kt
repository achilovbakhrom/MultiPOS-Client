package com.jim.multipos.core

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jim.multipos.R
import kotlinx.android.synthetic.main.search_header_fragment.*

abstract class SearchHeaderFragment<T: ViewDataBinding, V: BaseViewModel>: BaseFragment<T, V>() {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDefaultTopBar()
    }


    override fun getLayoutId(): Int {
        return R.layout.search_header_fragment
    }

    fun isCustomTopBar(): Boolean = false

    private fun initDefaultTopBar() {
        val topBarLayoutId = if (isCustomTopBar()) {
            getCustomTopBarLayoutId()
        }  else {
            R.layout.search_default_top_bar_layout
        }
        if (topBarLayoutId != -1) {
            LayoutInflater.from(context).inflate(topBarLayoutId, llHeader, true)
        } else {
            throw Exception(message = "Layout id is not initialized")
        }
    }

    protected fun getCustomTopBarLayoutId() : Int = -1
}