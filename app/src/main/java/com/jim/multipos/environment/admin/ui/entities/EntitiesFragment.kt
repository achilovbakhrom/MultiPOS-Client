package com.jim.multipos.environment.admin.ui.entities

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.core.fragments.TabbedFragment
import com.jim.multipos.databinding.TabbedFragmentBinding
import com.jim.multipos.environment.admin.ui.SearchViewModel
import com.jim.multipos.environment.admin.ui.entities.productclass.ProductClassFragment
import com.jim.multipos.environment.admin.ui.entities.products.ProductFragment
import javax.inject.Inject

class EntitiesFragment : TabbedFragment<TabbedFragmentBinding, EmptyViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory


    override fun getTabData(): Map<String, Fragment> = mutableMapOf<String, Fragment>(
            "Product" to ProductClassFragment(),
            "Product Class" to Fragment(),
            "Discount" to Fragment(),
            "Service Fee" to Fragment(),
            "Unit" to Fragment(),
            "Product Category" to Fragment(),
            "Import" to Fragment(),
            "Export" to Fragment()
    )

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getViewModel(): EmptyViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EmptyViewModel::class.java)
        return mViewModel as EmptyViewModel
    }

}