package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminEstablishmentLayoutBinding
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.adapter.EstablishmentAdapter
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.adapter.EstablishmentPosAdapter
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.viewmodel.EstablishmentViewModel
import kotlinx.android.synthetic.main.admin_establishment_layout.*
import javax.inject.Inject

class EstablishmentFragment: BaseFragment<AdminEstablishmentLayoutBinding, EstablishmentViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: EstablishmentViewModel? = null
    private var mViewDataBinding: AdminEstablishmentLayoutBinding? = null
    private var adapter: EstablishmentAdapter?=null
    private var posAdapter: EstablishmentPosAdapter?=null
    private var lastItem: String?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_establishment_layout
    }

    override fun getViewModel(): EstablishmentViewModel {
        mViewModel = ViewModelProviders.of( this, mViewModelFactory).get(EstablishmentViewModel::class.java)
        return mViewModel as EstablishmentViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()

        setUpEstablishmentRV()
        setUpPosRV()
        setUpEditor()
    }

    private fun setUpEstablishmentRV(){
        adapter = EstablishmentAdapter()
        adapter!!.listener = object : BaseClickListener<String> {
            override fun onItemClick(item: String) {
                tvEstablishmentName.text = item
                posAdapter?.lastPos = -1
                mViewModel?.fetchPos(item)
                mViewModel?.establishmentName?.set(item)
                lastItem = item
                mViewModel?.isPosMode?.set(false)
            }
        }
        rvEstablishment.itemAnimator.changeDuration = 0
        rvEstablishment.layoutManager = LinearLayoutManager(context)
        rvEstablishment.adapter = adapter
        mViewModel?.getEstablishmentItems()?.observe(this, Observer { adapter?.setItems(it!!) })
    }

    private fun setUpPosRV(){
        posAdapter = EstablishmentPosAdapter()
        posAdapter?.listener = object : BaseClickListener<String> {
            override fun onItemClick(item: String) {
                tvPosName.text = item
                mViewModel?.isPosMode?.set(true)
            }
        }
        rvPos.itemAnimator.changeDuration = 0
        rvPos.layoutManager = LinearLayoutManager(context)
        rvPos.adapter = posAdapter
        mViewModel?.getEstablishmentPosItems()?.observe(this, Observer { posAdapter?.setItems(it!!) })
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
                mViewModel?.establishmentName?.set("")
                mViewModel?.establishmentName?.set(lastItem)
            }//delete request
        }
    }
}