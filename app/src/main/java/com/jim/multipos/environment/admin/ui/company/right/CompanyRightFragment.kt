package com.jim.multipos.environment.admin.ui.company.right

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.baseAddEditFragment.AddEditModes
import com.jim.multipos.databinding.CompanyRightFragmentBinding
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.ui.company.right.show.CompanyShowMainFragment
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_right_fragment.*
import javax.inject.Inject

class CompanyRightFragment: BaseFragment<CompanyRightFragmentBinding, CompanyRightViewModel>(), Notifiable {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val COMPANY_RIGHT_FRAGMENT_TAG = "COMPANY_RIGHT_FRAGMENT_TAG"

    var companyDTO: CompanyDTO? = null

    var mode: AddEditModes = AddEditModes.EMPTY
        set(value) {
            when (value) {
                AddEditModes.EMPTY -> {
                    flCompanyMainContent.visibility = View.GONE
                    flCompanyEmptyLayout.visibility = View.VISIBLE
                }
                AddEditModes.INFO -> {
                    flCompanyMainContent.visibility = View.VISIBLE
                    flCompanyEmptyLayout.visibility = View.GONE
                    val fragment = CompanyShowMainFragment()
                    val bundle = Bundle()
                    bundle.putSerializable("model", companyDTO)
                    fragment.arguments = bundle
                    openFragment(fragment)
                }
                AddEditModes.ADD_EDIT -> {
                    flCompanyMainContent.visibility = View.VISIBLE
                    flCompanyEmptyLayout.visibility = View.GONE
                }
            }
            field = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mode = AddEditModes.ADD_EDIT
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_right_fragment
    override fun getViewModel(): CompanyRightViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyRightViewModel::class.java]
        return mViewModel as CompanyRightViewModel
    }

    override fun notify(action: String?, data: Any?) {
        when(action) {
            FragmentCommunicationOperations.ITEM_SELECTED.operation -> {
                val companyDTO = data as CompanyDTO
                mode = AddEditModes.INFO
            }
        }

    }


    private fun openFragment(fragment: Fragment) {
        var appCompatActivity = context as AppCompatActivity
        appCompatActivity
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.flCompanyMainContent, fragment, COMPANY_RIGHT_FRAGMENT_TAG)
                .commit()
    }

}

