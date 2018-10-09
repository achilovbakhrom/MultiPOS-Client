package com.jim.multipos.environment.admin.ui.establishment.center

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.EstablishmentCenterFragmentBinding
import javax.inject.Inject

class EstablishmentCenterFragment: BaseFragment<EstablishmentCenterFragmentBinding, EstablishmentCenterViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: EstablishmentCenterViewModel? = null
    private var mViewDataBinding: EstablishmentCenterFragmentBinding? = null
//    private var adapter: EstablishmentPosAdapter?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.establishment_center_fragment
    }

    override fun getViewModel(): EstablishmentCenterViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EstablishmentCenterViewModel::class.java)
        return mViewModel as EstablishmentCenterViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
//        adapter = EstablishmentPosAdapter()
//        adapter?.listener = object : BaseActions<String>{
//            override fun onItemClick(item: String) {
//
//            }
//        }
//        rvEstablishmentPos.itemAnimator.changeDuration = 0
//        rvEstablishmentPos.layoutManager = LinearLayoutManager(context)
//        rvEstablishmentPos.adapter = adapter
//        fetchData()
    }

    fun fetchData(){
//        mViewModel?.fetch()
//        mViewModel?.getEstablishmentItems()?.observe(this, Observer { adapter?.setItems(it!!) })
    }

}