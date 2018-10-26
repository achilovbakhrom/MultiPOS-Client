package com.jim.multipos.core.fragments

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jim.multipos.core.BackPressable
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.notify
import dagger.android.support.AndroidSupportInjection


abstract class BaseFragment<T: ViewDataBinding, V: BaseViewModel> : Fragment(), BackPressable {

    internal var mViewDataBinding: T? = null
    internal var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewModel?.onCreate(savedInstanceState)
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

    internal fun addFragment(fragment: Fragment?, tag: String, id: Int) {
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.add(id, fragment, tag)
                ?.commit()
    }

    internal fun replaceFragment(fragment: Fragment?, tag: String, id: Int){
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment, tag)
                ?.commit()
    }

    internal fun removeFragment(tag: String) {
        activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(activity?.supportFragmentManager?.findFragmentByTag(tag))
                ?.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mViewModel?.onSaveInstanceState(outState)

    }

    internal fun sendNotification(tag: String, action: String? = null, data: Any? = null) {
        if (activity != null && activity is AppCompatActivity) {
            (activity as AppCompatActivity).notify(tag = tag, action = action, data = data)
        }
    }

    internal fun sendNotification(fragment: Fragment, action: String? = null, data: Any? = null) {
        if (activity != null && activity is AppCompatActivity) {
            (activity as AppCompatActivity).notify(fragment = fragment, action = action, data = data)
        }
    }

}