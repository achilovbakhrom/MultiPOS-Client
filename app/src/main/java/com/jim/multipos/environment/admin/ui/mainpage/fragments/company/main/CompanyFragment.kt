package com.jim.multipos.environment.admin.ui.mainpage.fragments.company.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.DoubleHorizontalFragment
import com.jim.multipos.databinding.SearchHeaderFragmentBinding
import com.jim.multipos.environment.admin.ui.mainpage.MainPageViewModel
import com.jim.multipos.environment.admin.ui.mainpage.SearchViewModel
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.CompanyViewModel
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.right.CompanyRightFragment
import javax.inject.Inject


//class CompanyFragment: BaseFragment<AdminCompanyFragmentBinding, CompanyViewModel>() {
//
//    @Inject
//    lateinit var mViewModelFactory: ViewModelProvider.Factory
//
//    private var mViewModel: CompanyViewModel? = null
//    private var mViewDataBinding: AdminCompanyFragmentBinding? = null
//    private var items: List<String> = ArrayList()
//    private var adapter: CompanyAdapter?=null
//    private var lastItem: String?=null
//    var dialog: View?=null
//    var dialogVisible = false
//
//    override fun getBindingVariable(): Int {
//        return BR.viewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.admin_company_fragment
//    }
//
//    override fun getViewModel(): CompanyViewModel {
//        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyViewModel::class.java)
//        return mViewModel as CompanyViewModel
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        mViewDataBinding = getViewDataBinding()
//        setUp()
//        ivCompany.setOnClickListener { showDialog() }
//    }
//
//    private fun setUp(){
//        dialog = LayoutInflater.from(context).inflate(R.layout.dialog, null, false)
//        dialog!!.layoutParams = RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
//        dialog!!.findViewById<ImageView>(R.id.ivBack).setColorFilter(ContextCompat.getColor(context!!, R.color.colorGray), PorterDuff.Mode.SRC_IN)
//        dialog!!.findViewById<ImageView>(R.id.ivBack).setOnClickListener {
//            if(dialogVisible) {
//                rlEditor.removeView(dialog)
//                dialogVisible = false
//            }
//        }
//
//        mViewDataBinding!!.root.setOnClickListener {
//            if(dialogVisible) {
//                rlEditor.removeView(dialog)
//                dialogVisible = false
//            }
//        }
//        adapter = CompanyAdapter(context!!)
//        adapter?.listener = object : BaseClickListener<String> {
//            override fun onItemClick(item: String) {
//                mViewModel?.companyName?.set(item)
//                lastItem = item
//                if(dialogVisible) {
//                    rlEditor.removeView(dialog)
//                    dialogVisible = false
//                }
//            }
//        }
//        rvCompanies.itemAnimator.changeDuration = 0
//        rvCompanies.layoutManager = GridLayoutManager(context, 2)
//        rvCompanies.adapter = adapter
//        mViewModel?.getLiveData()?.observe(this, Observer {
//            items = it!!
//            adapter?.setItems(it as ArrayList)
//        })
//
//        btnEdit.setOnClickListener {
//            if(btnEdit.text == getString(R.string.edit))
//                mViewModel?.isEditable?.set(true)
//            //else netWorkRequest
//        }
//
//        btnDelete.setOnClickListener {
//            if(btnDelete.text == getString(R.string.cancel)) {
//                mViewModel?.isEditable?.set(false)
//                mViewModel?.companyName?.set("")
//                mViewModel?.companyName?.set(lastItem)
//            }//delete request
//        }
//
//        ivCard.setOnClickListener {
//            ivCard.setColorFilter(ContextCompat.getColor(context!!, R.color.colorNavy), PorterDuff.Mode.SRC_IN)
//            ivList.setColorFilter(ContextCompat.getColor(context!!, R.color.colorGray), PorterDuff.Mode.SRC_IN)
//        }
//        ivList.setOnClickListener {
//            ivList.setColorFilter(ContextCompat.getColor(context!!, R.color.colorNavy), PorterDuff.Mode.SRC_IN)
//            ivCard.setColorFilter(ContextCompat.getColor(context!!, R.color.colorGray), PorterDuff.Mode.SRC_IN)
//        }
//    }
//
//    private fun showDialog(){
//        if(!dialogVisible) {
//            rlEditor.addView(dialog)
//        }
//        dialogVisible = true
//    }
//}

class CompanyFragment: DoubleHorizontalFragment<SearchHeaderFragmentBinding, SearchViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    private var mViewModel: SearchViewModel? = null

    override fun getLayoutId(): Int {
       return R.layout.search_header_fragment
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SearchViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel::class.java)
        return mViewModel as SearchViewModel
    }

    override fun getLeftFragment(): Fragment? {
        return CompanyLeftFragment()
    }

    override fun getRightFragment(): Fragment? {
        return CompanyRightFragment()
    }

    override fun isCustomTopBar(): Boolean { return false }

    override fun getCustomTopBarLayoutId(): Int {
        return super.getCustomTopBarLayoutId()
    }



}