package com.jim.multipos.environment.admin.ui.mainpage.fragments.company.right

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseFragment
import com.jim.multipos.databinding.CompanyRightFragmentBinding
import kotlinx.android.synthetic.main.company_right_fragment.*
import javax.inject.Inject

class CompanyRightFragment: BaseFragment<CompanyRightFragmentBinding, CompanyRightViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: CompanyRightViewModel? = null
    private var mViewDataBinding: CompanyRightFragmentBinding? = null
    private var lastItem: String?=null


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.company_right_fragment
    }

    override fun getViewModel(): CompanyRightViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyRightViewModel::class.java)
        return mViewModel as CompanyRightViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        btnEdit.setOnClickListener {
                        if(btnEdit.text == getString(R.string.edit))
                mViewModel?.isEditable?.set(true)
            //else netWorkRequest
        }

        btnDelete.setOnClickListener {
            if(btnDelete.text == getString(R.string.cancel)) {
                mViewModel?.isEditable?.set(false)
                mViewModel?.companyName?.set("")
                mViewModel?.companyName?.set(lastItem)
            }//delete request
        }
    }

}