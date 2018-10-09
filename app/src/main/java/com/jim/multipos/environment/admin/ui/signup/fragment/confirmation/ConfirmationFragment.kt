package com.jim.multipos.environment.admin.ui.signup.fragment.confirmation

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSignupConfirmationLayoutBinding
import com.jim.multipos.environment.admin.ui.signup.SignUpViewModel
import com.jim.multipos.core.fragments.BaseFragment
import kotlinx.android.synthetic.main.admin_signup_info_fragment_layout.*
import javax.inject.Inject

class ConfirmationFragment: BaseFragment<AdminSignupConfirmationLayoutBinding, SignUpViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: SignUpViewModel? = null
    private var mViewDataBinding: AdminSignupConfirmationLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_confirmation_layout
    }

    override fun getViewModel(): SignUpViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(SignUpViewModel::class.java)
        return mViewModel as SignUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        backBtn.setOnClickListener { activity!!.onBackPressed() }
    }
}