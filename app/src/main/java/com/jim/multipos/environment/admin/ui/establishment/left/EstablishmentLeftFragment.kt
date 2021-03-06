package com.jim.multipos.environment.admin.ui.establishment.left

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseFragment
import com.jim.multipos.databinding.EstablishmentLeftFragmentBinding
import com.jim.multipos.environment.admin.ui.establishment.adapter.EstablishmentAdapter
import kotlinx.android.synthetic.main.establishment_left_fragment.*
import javax.inject.Inject

class EstablishmentLeftFragment: BaseFragment<EstablishmentLeftFragmentBinding, EstablishmentLeftViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: EstablishmentLeftViewModel? = null
    private var mViewDataBinding: EstablishmentLeftFragmentBinding? = null
    private var adapter: EstablishmentAdapter?=null


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.establishment_left_fragment
    }

    override fun getViewModel(): EstablishmentLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EstablishmentLeftViewModel::class.java)
        return mViewModel as EstablishmentLeftViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        adapter = EstablishmentAdapter()
        adapter?.listener = object : BaseClickListener<String> {
            override fun onItemClick(item: String) {
                mViewModel?.establishmentName?.set(item)

            }
        }
        rvEstablishment.itemAnimator.changeDuration = 0
        rvEstablishment.layoutManager = LinearLayoutManager(context)
        rvEstablishment.adapter = adapter
        mViewModel?.getEstablishmentItems()?.observe(this, Observer { adapter?.setItems(it!!) })
    }
}