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

    @Inject
    lateinit var prefsManager: PrefsManager

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
        mSignInViewModel?.signInLiveData?.observe(this, Observer {
            prefsManager.putValue("access_token", it?.data?.access_token)
            prefsManager.putValue("refresh_token", it?.data?.refresh_token)
            prefsManager.putValue("refresh_expires_in", it?.data?.refresh_expires_in)
        })
    }

    fun signIn(view: View){
//        startActivity(Intent(this, MainPageActivity::class.java))
        mSignInViewModel?.signIn(etUsername.text.toString(), etPassword.text.toString(),
                "password",
                "token-client",
                "bf9f3da-8357-40b9-a2a9-ef9c875394a0")
    }

    fun SignUp(view: View){
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}