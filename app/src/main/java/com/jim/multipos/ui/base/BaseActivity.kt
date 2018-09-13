package com.jim.multipos.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.jim.multipos.R
import com.jim.multipos.ui.adminsignup.fragment.general.GeneralFragment
import dagger.android.AndroidInjection


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewDataBinding: T
    var mViewModel: V?=null

    abstract fun getBindingVariable():Int
    abstract fun getLayoutId():Int
    abstract fun getViewModel():V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        performDataBinding()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    private fun performDataBinding(){
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    fun addFragment(fragment: Fragment, layoutId: Int){
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack(null)
                .commit()
    }

    fun addFragmentWithoutBackStack(fragment: Fragment, layoutId: Int){
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .commit()
    }
}