package com.jim.multipos.environment.admin.ui.entities.productclass.productClassAddEdit

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseAddEditFragment
import com.jim.multipos.databinding.ProductClassListFragmentBinding
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassList.ProductClassListViewModel
import javax.inject.Inject

class ProductClassAddEditFragment: BaseAddEditFragment<ProductClassListFragmentBinding, ProductClassListViewModel>() {


    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState?.getInt("page") != null) {
            mViewModel?.page?.set(savedInstanceState.getInt("page"))
        }
    }

    override fun getContentLayoutId(): Int = R.layout.product_class_add_edit_fragment

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel?.page?.get()!!)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): ProductClassListViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassListViewModel::class.java)
        return mViewModel as ProductClassListViewModel
    }

    override fun initObservers() {

    }

    override fun delete() {
        Log.d("sss", "delete")
    }

    override fun editModeCanceled() {
        Log.d("sss", "editModeCanceled")
    }

    override fun newAddModeCanceled() {
        Log.d("sss", "newAddModeCanceled")
    }

    override fun newItemAddClicked() {
        Log.d("sss", "newItemAddClicked")
    }

    override fun onAddMode() {
        Log.d("sss", "onAddMode")

    }

    override fun editItemSaveClicked() {
        Log.d("sss", "editItemSaveClicked")
    }

    override fun onEditMode() {
        Log.d("sss", "onEditMode")
    }

}
