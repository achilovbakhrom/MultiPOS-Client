package com.jim.multipos.environment.admin.ui.entities

import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.core.fragments.TabbedFragment
import com.jim.multipos.databinding.TabbedFragmentBinding
import com.jim.multipos.environment.admin.ui.entities.productclass.ProductClassFragment
import com.jim.multipos.environment.admin.ui.entities.products.ProductFragment
import javax.inject.Inject

class EntitiesFragment : TabbedFragment<TabbedFragmentBinding, EmptyViewModel>() {

    @Inject
    lateinit var emptyViewModel: EmptyViewModel

    override fun getTabData(): Map<String, Fragment> = mutableMapOf<String, Fragment>(
            "Product" to ProductFragment(),
            "Product Class" to ProductClassFragment(),
            "Discount" to ProductClassFragment(),
            "Service Fee" to ProductClassFragment(),
            "Unit" to ProductClassFragment(),
            "Product Category" to ProductClassFragment(),
            "Import" to ProductClassFragment(),
            "Export" to ProductClassFragment()
    )

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getViewModel(): EmptyViewModel = emptyViewModel
    override fun getDefaultTabName(): String? =  "Product Class"

}