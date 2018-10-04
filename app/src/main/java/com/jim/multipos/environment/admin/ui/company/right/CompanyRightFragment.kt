package com.jim.multipos.environment.admin.ui.company.right

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseFragment
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.NotificationActions
import com.jim.multipos.customView.CustomMpDialog
import com.jim.multipos.databinding.CompanyRightFragmentBinding
import com.jim.multipos.environment.admin.ui.MainPageActivity
import com.jim.multipos.environment.admin.ui.MainPageActivity.Companion.COMPANY_FRAGMENT
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import kotlinx.android.synthetic.main.company_right_fragment.*
import javax.inject.Inject

class CompanyRightFragment: BaseFragment<CompanyRightFragmentBinding, CompanyRightViewModel>(), Notifiable{

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

        dialog.listener = object : CustomMpDialog.DialogListener{
            override fun onBack() {
                activity?.onBackPressed()
            }

            override fun onClick() {
                if(!dialog.isVisible) {
                    dialog.showDialog(view?.parent as FrameLayout, "MY", listOf("asd", "sad"))
                    if (activity is MainPageActivity)
                        (activity as MainPageActivity).isDialogOpened = true
                    dialog.isVisible = true
                }
            }
        }



        btnEdit.setOnClickListener {
                        if(btnEdit.text == getString(R.string.edit))
                            mViewModel?.isEditable?.set(true)
                        else {
                            val fragment = activity?.supportFragmentManager?.findFragmentByTag(COMPANY_FRAGMENT)
                            (fragment as? CompanyFragment)?.updateRV()
                        }
        }

        btnDelete.setOnClickListener {
            if(btnDelete.text == getString(R.string.cancel)) {
                mViewModel?.isEditable?.set(false)
                mViewModel?.companyName?.set("")
                mViewModel?.companyName?.set(lastItem)
            }//delete request
        }
    }

    override fun onBackPressed() {
        dialog.dismiss(view?.parent as FrameLayout)
    }

    override fun notify(action: String?, data: Any?) {
        when(action){
            NotificationActions.POPULATE.value()->etCompanyName.setText(data.toString())
        }
    }
}