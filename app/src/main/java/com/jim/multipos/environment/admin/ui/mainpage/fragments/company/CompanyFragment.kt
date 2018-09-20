package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseFragment
import com.jim.multipos.databinding.AdminCompanyFragmentBinding
import kotlinx.android.synthetic.main.admin_company_fragment.*
import javax.inject.Inject


class CompanyFragment: BaseFragment<AdminCompanyFragmentBinding, CompanyViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: CompanyViewModel? = null
    private var mViewDataBinding: AdminCompanyFragmentBinding? = null
    private var items: List<String> = ArrayList()
    private var adapter: CompanyAdapter?=null
    private var lastItem: String?=null
    var dialog: View?=null
    var dialogVisible = false

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_company_fragment
    }

    override fun getViewModel(): CompanyViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyViewModel::class.java)
        return mViewModel as CompanyViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        setUp()
        ivCompany.setOnClickListener { showDialog() }
    }

    private fun setUp(){
        dialog = LayoutInflater.from(context).inflate(R.layout.dialog, null, false)
        dialog!!.layoutParams = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        mViewDataBinding!!.root.setOnClickListener {
            if(dialogVisible) {
                rlEditor.removeView(dialog)
                dialogVisible = false
            }
        }
        adapter = CompanyAdapter(context!!)
        adapter?.listener = object : BaseClickListener<String> {
            override fun onItemClick(item: String) {
                mViewModel?.companyName?.set(item)
                lastItem = item
                if(dialogVisible) {
                    rlEditor.removeView(dialog)
                    dialogVisible = false
                }
            }
        }
        rvCompanies.itemAnimator.changeDuration = 0
        rvCompanies.layoutManager = GridLayoutManager(context, 2)
        rvCompanies.adapter = adapter
        mViewModel?.getLiveData()?.observe(this, Observer {
            items = it!!
            adapter?.setItems(it as ArrayList)
        })

        btnEdit.setOnClickListener {
            if(btnEdit.text == getString(R.string.edit))
                mViewModel?.isEditable?.set(true)
            //else netWorkRequest
        }

        btnDelete.setOnClickListener {
            if(btnDelete.text == getString(R.string.cancel)) {
                mViewModel?.isEditable?.set(false)
                mViewModel?.companyName?.set("")
                mViewModel?.companyName?.set(lastItem)
            }//delete request
        }
    }

    private fun showDialog(){
        if(!dialogVisible) {
            rlEditor.addView(dialog)
        }
        dialogVisible = true
    }
}