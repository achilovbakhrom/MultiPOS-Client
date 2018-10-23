package com.jim.multipos.environment.admin.ui.company.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.core.fragments.DoubleHorizontalFragment
import com.jim.multipos.databinding.SearchHeaderFragmentBinding
import com.jim.multipos.environment.admin.ui.SearchViewModel
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightFragment
import javax.inject.Inject


class CompanyFragment: DoubleHorizontalFragment<SearchHeaderFragmentBinding, SearchViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SearchViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel::class.java)
        return mViewModel as SearchViewModel
    }

    override fun getLeftFragment(): Fragment? {
        return CompanyLeftFragment()
    }

    override fun getRightFragment(): Fragment? {
        return CompanyRightFragment()
    }

    override fun isCustomTopBar(): Boolean { return false }

    fun updateRV(){
        val fragment = activity?.supportFragmentManager?.findFragmentByTag(LEFT_FRAGMENT_TAG)
//        if(fragment!=null && fragment is CompanyLeftFragment)
//            fragment.update()

    }



}