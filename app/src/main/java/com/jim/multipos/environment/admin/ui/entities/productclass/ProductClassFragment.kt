package com.jim.multipos.environment.admin.ui.entities.productclass

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.core.fragments.DoubleHorizontalFragment
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.databinding.TabbedFragmentBinding
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassList.ProductClassListFragment
import javax.inject.Inject

class ProductClassFragment: DoubleHorizontalFragment<TabbedFragmentBinding, EmptyViewModel>() {


    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var mViewModel: EmptyViewModel? = null

    override fun getLeftFragment(): Fragment? {
        return ProductClassListFragment()
    }

    override fun getRightFragment(): Fragment? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBindingVariable(): Int = BR.viewModel


    override fun getViewModel(): EmptyViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EmptyViewModel::class.java)
        return mViewModel as EmptyViewModel
    }

}