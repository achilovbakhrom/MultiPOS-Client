package com.jim.multipos.ui.adminmainpage.fragments.productclass

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminProductClassLayoutBinding
import com.jim.multipos.ui.base.BaseClickListener
import com.jim.multipos.ui.base.BaseFragment
import kotlinx.android.synthetic.main.admin_product_class_layout.*
import javax.inject.Inject

class ProductClassFragment:BaseFragment<AdminProductClassLayoutBinding, ProductClassViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: ProductClassViewModel? = null
    private var mViewDataBinding: AdminProductClassLayoutBinding? = null

    private var adapter: ProductClassAdapter?=null
    private var lastItem: String?=null


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_product_class_layout
    }

    override fun getViewModel(): ProductClassViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassViewModel::class.java)
        return mViewModel as ProductClassViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()

        setUp()
        setUpEditor()
    }

    private fun setUp(){
        adapter = ProductClassAdapter()
        adapter!!.listener = object : BaseClickListener<String>{
            override fun onItemClick(item: String) {
                lastItem = item
                mViewModel!!.productClassName.set(item)
            }

        }
        rvProductClass.itemAnimator.changeDuration = 0
        rvProductClass.layoutManager = GridLayoutManager(context, 2)
        rvProductClass.adapter = adapter
        mViewModel!!.getProductClassItems().observe(this, Observer { adapter!!.setItems(it!!) })
    }

    private fun setUpEditor(){
        btnEdit.setOnClickListener {
            if(btnEdit.text == getString(R.string.edit))
                mViewModel!!.isEditable.set(true)
            //else netWorkRequest
        }
        btnDelete.setOnClickListener {
            if(btnDelete.text == getString(R.string.cancel)) {
                mViewModel!!.isEditable.set(false)
                mViewModel!!.productClassName.set("")
                mViewModel!!.productClassName.set(lastItem)
            }//delete request
        }
    }
}