package com.jim.multipos.environment.admin.ui.signin

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSigninLayoutBinding
import com.jim.multipos.core.BaseActivity
import com.jim.multipos.environment.admin.ui.MainPageActivity
import com.jim.multipos.environment.admin.ui.signup.AdminSignUpActivity
import javax.inject.Inject

class AdminSignInActivity: BaseActivity<AdminSigninLayoutBinding, AdminSignInViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mSignInViewModel: AdminSignInViewModel?=null

    private var mSingInActivityDataBinding: AdminSigninLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signin_layout
    }

    override fun getViewModel(): AdminSignInViewModel {
        mSignInViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AdminSignInViewModel::class.java)
        return mSignInViewModel as AdminSignInViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSingInActivityDataBinding = getViewDataBinding()
        mSingInActivityDataBinding?.toolbar?.setOnBackButtonClick { onBackPressed() }
    }

    fun SignIn(view: View){
        startActivity(Intent(this, MainPageActivity::class.java))
    }

    fun SignUp(view: View){
        startActivity(Intent(this, AdminSignUpActivity::class.java))
    }
}