package com.jim.multipos.environment.admin.ui.signup.fragment.general

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSignupGeneralFragmentLayoutBinding
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.environment.admin.ui.signup.SignUpViewModel
import com.jim.multipos.environment.admin.ui.signup.model.SignUpModel
import com.jim.multipos.utils.isEmailValid
import kotlinx.android.synthetic.main.admin_signup_general_fragment_layout.*
import javax.inject.Inject

class GeneralFragment: BaseFragment<AdminSignupGeneralFragmentLayoutBinding, SignUpViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: SignUpViewModel? = null
    private var mViewDataBinding: AdminSignupGeneralFragmentLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_general_fragment_layout
    }

    override fun getViewModel(): SignUpViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(SignUpViewModel::class.java)
        return mViewModel as SignUpViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        mViewDataBinding!!.nextBtn.setOnClickListener {
            //if(checkValidation())
                mViewModel!!.setGeneralData(SignUpModel(etLogin.text.toString(), etPassword.text.toString()))
        }

        backBtn.setOnClickListener { activity!!.onBackPressed() }
    }

    fun checkValidation():Boolean{
        if(!isEmailValid(mViewDataBinding!!.etLogin.text.toString())) {
            mViewDataBinding!!.etLogin.error = getString(R.string.incorrect_email)
            return false
        }else if (!mViewDataBinding!!.etPassword.text.toString().equals(mViewDataBinding!!.etConfirmPassword.text.toString())){
            mViewDataBinding!!.etConfirmPassword.error = getString(R.string.password_dont_match)
            return false
        }else if(mViewDataBinding!!.etPassword.text.toString().length<6){
            mViewDataBinding!!.etPassword.error = getString(R.string.password_length)
            return false
        }else return true
    }
}