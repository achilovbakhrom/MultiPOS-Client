package com.jim.multipos.core

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private var mViewDataBinding: T? = null
    var mViewModel: V? = null
    var isDialogOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        performDataBinding()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        if (savedInstanceState != null) {
            isDialogOpened = savedInstanceState.getBoolean("isDialogOpened", false)
        }
    }

    fun getViewDataBinding(): T? {
        return mViewDataBinding
    }

    private fun performDataBinding(){
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
    }

    fun addFragment(fragment: Fragment, layoutId: Int){
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun addFragmentWithoutTag(fragment: Fragment, layoutId: Int){
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .commit()
    }

    fun addFragmentWithoutBackStack(fragment: Fragment, layoutId: Int, tag: String){
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment, tag)
                .commit()
    }

    abstract fun getBindingVariable() : Int
    abstract fun getLayoutId() : Int
    abstract fun getViewModel() : V?



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean("isDialogOpened", isDialogOpened)
    }



    override fun onBackPressed() {
        if (isDialogOpened) {
            val fragments = this.supportFragmentManager?.fragments
            if (fragments != null) {
                for (fragment in fragments) {
                    if (fragment is BackPressable) {
                        fragment.onBackPressed()
                        isDialogOpened = false
                    }
                }
            }
        } else {
            super.onBackPressed()
        }
    }

}