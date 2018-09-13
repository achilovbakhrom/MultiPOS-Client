package com.jim.multipos.ui.adminsignup.fragment.confirmation

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSignupConfirmationLayoutBinding
import com.jim.multipos.ui.adminsignup.AdminSignUpViewModel
import com.jim.multipos.ui.base.BaseFragment
import kotlinx.android.synthetic.main.admin_signup_info_fragment_layout.*
import javax.inject.Inject

class ConfirmationFragment: BaseFragment<AdminSignupConfirmationLayoutBinding, AdminSignUpViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: AdminSignUpViewModel? = null
    private var mViewDataBinding: AdminSignupConfirmationLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_confirmation_layout
    }

    override fun getViewModel(): AdminSignUpViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(AdminSignUpViewModel::class.java)
        return mViewModel as AdminSignUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        backBtn.setOnClickListener { activity!!.onBackPressed() }
    }
}