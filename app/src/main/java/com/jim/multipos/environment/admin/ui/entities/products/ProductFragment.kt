package com.jim.multipos.environment.admin.ui.entities.products

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminProductLayoutBinding
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseFragment
import kotlinx.android.synthetic.main.admin_product_layout.*
import javax.inject.Inject

class ProductFragment: BaseFragment<AdminProductLayoutBinding, ProductViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: ProductViewModel? = null
    private var mViewDataBinding: AdminProductLayoutBinding? = null

    private var adapter: ProductsAdapter?=null
    private var lastItem: String?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_product_layout
    }

    override fun getViewModel(): ProductViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductViewModel::class.java)
        return mViewModel as ProductViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()

        setUp()
        setUpEditor()
    }

    private fun setUp(){
        spinnerCategory.setItems(arrayOf("asdas", "123asd", "sdas123"))
        spinnerCategory.setItemSelected {  }
        spinnerSubCategory.setItems(arrayOf("lhf", "9832", "fkfdk"))
        spinnerSubCategory.setItemSelected {  }
        spinnerProductClass.setItems(arrayOf("poret", "llol", "00923"))
        spinnerProductClass.setItemSelected {  }

        adapter = ProductsAdapter()
        adapter?.listener = object : BaseClickListener<String> {
            override fun onItemClick(item: String) {
                mViewModel?.productName?.set(item)
                lastItem = item
            }

        }

        rvProducts.itemAnimator.changeDuration = 0
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = adapter
        mViewModel?.getProductItems()?.observe(this, Observer { adapter?.setItems(it!!) })
    }

    private fun setUpEditor(){
        btnEdit.setOnClickListener {
            if(btnEdit.text == getString(R.string.edit))
                mViewModel?.isEditable?.set(true)
            //else netWorkRequest
        }
        btnDelete.setOnClickListener {
            if(btnDelete.text == getString(R.string.cancel)) {
                mViewModel?.isEditable?.set(false)
                mViewModel?.productName?.set("")
                mViewModel?.productName?.set(lastItem)
            }//delete request
        }
    }
}