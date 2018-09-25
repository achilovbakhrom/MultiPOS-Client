package com.jim.multipos.core

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel> : Fragment() {
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    fun getViewDataBinding(): T? = mViewDataBinding
    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId() : Int
    abstract fun getViewModel() : V

}