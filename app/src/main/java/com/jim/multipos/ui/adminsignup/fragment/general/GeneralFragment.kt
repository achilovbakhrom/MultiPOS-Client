package com.jim.multipos.ui.adminsignup.fragment.general

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSignupGeneralFragmentLayoutBinding
import com.jim.multipos.ui.adminsignup.AdminSignUpViewModel
import com.jim.multipos.ui.adminsignup.model.SignUpModel
import com.jim.multipos.ui.base.BaseFragment
import com.jim.multipos.utils.Utils
import kotlinx.android.synthetic.main.admin_signup_general_fragment_layout.*
import javax.inject.Inject

class GeneralFragment: BaseFragment<AdminSignupGeneralFragmentLayoutBinding, AdminSignUpViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: AdminSignUpViewModel? = null
    private var mViewDataBinding: AdminSignupGeneralFragmentLayoutBinding? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_general_fragment_layout
    }

    override fun getViewModel(): AdminSignUpViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(AdminSignUpViewModel::class.java)
        return mViewModel as AdminSignUpViewModel
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
        if(!Utils.isEmailValid(mViewDataBinding!!.etLogin.text.toString())) {
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