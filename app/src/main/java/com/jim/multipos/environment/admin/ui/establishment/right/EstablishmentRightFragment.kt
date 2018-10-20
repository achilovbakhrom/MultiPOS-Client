package com.jim.multipos.environment.admin.ui.establishment.right

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.EstablishmentRightFragmentBinding
import javax.inject.Inject

class EstablishmentRightFragment: BaseFragment<EstablishmentRightFragmentBinding, EstablishmentRightViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.establishment_right_fragment
    }

    override fun getViewModel(): EstablishmentRightViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EstablishmentRightViewModel::class.java)
        return mViewModel as EstablishmentRightViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
    }
}