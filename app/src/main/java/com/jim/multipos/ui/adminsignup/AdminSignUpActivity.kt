package com.jim.multipos.ui.adminsignup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminSignupLayoutBinding
import com.jim.multipos.ui.adminsignup.fragment.confirmation.ConfirmationFragment
import com.jim.multipos.ui.adminsignup.fragment.general.GeneralFragment
import com.jim.multipos.ui.adminsignup.fragment.info.InfoFragment
import com.jim.multipos.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.admin_signup_layout.*
import javax.inject.Inject


class AdminSignUpActivity : BaseActivity<AdminSignupLayoutBinding, AdminSignUpViewModel>(), HasSupportFragmentInjector{

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mSignUpViewModel: AdminSignUpViewModel?=null

    private var mSingUpActivityDataBinding: AdminSignupLayoutBinding? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector!!
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_signup_layout
    }

    override fun getViewModel(): AdminSignUpViewModel {
        mSignUpViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AdminSignUpViewModel::class.java)
        return mSignUpViewModel as AdminSignUpViewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSingUpActivityDataBinding = getViewDataBinding()
        addFragmentWithoutBackStack(GeneralFragment(), R.id.container)
        mSignUpViewModel!!.getGeneralData().observe(this, Observer{ response ->
            openInfoFragment(response!!.data!!.email, response!!.data!!.pass)
        })
        mSignUpViewModel!!.getInfoData().observe(this, Observer {
            openConfirmationFragment()
        })
    }

    fun openInfoFragment(email: String?, pass: String?){
        val fragment = InfoFragment()
        val args = Bundle()
        args.putString("email", email)
        args.putString("pass", pass)
        fragment.arguments = args
        addFragment(fragment, R.id.container)
        generalCircle.background = getDrawable(R.drawable.reg_completed_circle)
        infoCircle.background = getDrawable(R.drawable.reg_current_circle)
        gen2infoLine.background = getDrawable(R.color.colorNavy)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1) {
            generalCircle.background = getDrawable(R.drawable.reg_current_circle)
            infoCircle.background = getDrawable(R.drawable.reg_not_completed_circle)
            gen2infoLine.background = getDrawable(R.color.colorGray)
        }else if(supportFragmentManager.backStackEntryCount == 2){
            completeCircle.background = getDrawable(R.drawable.reg_not_completed_circle)
            infoCircle.background = getDrawable(R.drawable.reg_current_circle)
            info2completeLine.background = getDrawable(R.color.colorGray)
        }
        super.onBackPressed()
    }
    fun openConfirmationFragment(){
        addFragment(ConfirmationFragment(), R.id.container)
        infoCircle.background = getDrawable(R.drawable.reg_completed_circle)
        completeCircle.background = getDrawable(R.drawable.reg_current_circle)
        info2completeLine.background = getDrawable(R.color.colorNavy)
    }

}