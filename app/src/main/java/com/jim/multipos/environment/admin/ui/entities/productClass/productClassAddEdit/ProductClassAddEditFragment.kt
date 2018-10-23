package com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.baseAddEditFragment.AddEditModeButtonsClickListener
import com.jim.multipos.core.fragments.baseAddEditFragment.AddEditModes
import com.jim.multipos.core.fragments.baseAddEditFragment.BaseAddEditFragment
import com.jim.multipos.databinding.ProductClassListFragmentBinding
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.ui.SearchViewModel
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassListViewModel
import javax.inject.Inject

class ProductClassAddEditFragment:
        BaseAddEditFragment<ProductClass, ProductClassListFragmentBinding, ProductClassAddEditViewModel>(),
        AddEditModeButtonsClickListener {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mode = AddEditModes.EMPTY
    }

    override fun getEmptyString(): String = getString(R.string.select_to_view_info, "Product Class")
    override fun infoModeLayoutId(): Int = R.layout.product_class_info_fragment
    override fun addEditModeLayoutId(): Int = R.layout.product_class_add_edit_fragment
    override fun getBindingVariable(): Int = BR.viewModel
    override fun getViewModel(): ProductClassAddEditViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassAddEditViewModel::class.java)
        return mViewModel as ProductClassAddEditViewModel
    }

    override fun initObservers() {}
    override fun fillAddEditMode(item: ProductClass?) {}
    override fun fillInfoMode(item: ProductClass) {}

    /**
     *  Add edit mode buttons click listener
     */
    override fun onDeleteClick() {}
    override fun onEditClick() {}
    override fun onSaveClick() {}
    override fun onCancelAddEditClick() {}

}
