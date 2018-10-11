package com.jim.multipos.environment.admin.ui.entities.productclass.productClassList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jim.multipos.BR
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.databinding.ProductClassListFragmentBinding
import kotlinx.android.synthetic.main.single_list_fragment.*
import javax.inject.Inject

class ProductClassListFragment: SingleListFragment<ProductClassListFragmentBinding, ProductClassListViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mViewModel: ProductClassListViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState?.getInt("page") != null) {
            mViewModel.page.set(savedInstanceState.getInt("page"))
        }
        mViewModel.onViewCreated()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel.page.get()!!)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): ProductClassListViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassListViewModel::class.java)
        return mViewModel
    }

    override fun initRV() {
        rvSingle.setLoadingMoreEnabled(true)
        rvSingle.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                mViewModel.loadMore()
            }
            override fun onRefresh() {
                mViewModel.refresh()
            }
        })
    }

    override fun initObservers() {
        mViewModel.data.observe(this, Observer {

        })
    }

    override fun buttonAction() {


    }



}