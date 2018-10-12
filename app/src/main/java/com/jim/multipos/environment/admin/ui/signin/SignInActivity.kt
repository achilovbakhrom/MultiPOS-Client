package com.jim.multipos.environment.admin.ui.signin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActivity
import com.jim.multipos.databinding.AdminSigninLayoutBinding
import com.jim.multipos.environment.admin.ui.signup.SignUpActivity
import com.jim.multipos.utils.PrefsManager
import kotlinx.android.synthetic.main.admin_signin_layout.*
import javax.inject.Inject

class SignInActivity: BaseActivity<AdminSigninLayoutBinding, SignInViewModel>(){

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory



    private var mSignInViewModel: SignInViewModel?=null

    private var mSingInActivityDataBinding: AdminSigninLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signin_layout
    }

    override fun getViewModel(): SignInViewModel {
        mSignInViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SignInViewModel::class.java)
        return mSignInViewModel as SignInViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSingInActivityDataBinding = getViewDataBinding()
        mSingInActivityDataBinding?.toolbar?.setOnBackButtonClick { onBackPressed() }
        mSignInViewModel?.isLoading?.observe(this, Observer {

            if (it == true) {

            } else {

            }

        })

        mSignInViewModel?.errorMessage?.observe(this, Observer {
            if (it == null || it.isEmpty()) {

            } else {

            }
        })


    }

    fun signIn(view: View){
//        startActivity(Intent(this, MainPageActivity::class.java))
        mSignInViewModel?.signIn(etUsername.text.toString(), etPassword.text.toString(),
                "password",
                "token-client",
                "a680957e-ca57-4c40-aa9a-08157c5bae2c")
    }

    fun SignUp(view: View){
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}