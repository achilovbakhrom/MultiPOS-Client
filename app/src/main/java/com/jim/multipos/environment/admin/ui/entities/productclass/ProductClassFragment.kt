package com.jim.multipos.environment.admin.ui.entities.productclass

import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.core.DoubleHorizontalFragment
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.databinding.TabbedFragmentBinding

class ProductClassFragment: DoubleHorizontalFragment<TabbedFragmentBinding, EmptyViewModel>() {

    override fun getLeftFragment(): Fragment? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRightFragment(): Fragment? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBindingVariable(): Int = BR.viewModel


    override fun getViewModel(): EmptyViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    @Inject
//    lateinit var mViewModelFactory: ViewModelProvider.Factory
//
//    private var mViewModel: ProductClassViewModel? = null
//    private var mViewDataBinding: AdminProductClassLayoutBinding? = null
//
//    private var adapter: ProductClassAdapter?=null
//    private var lastItem: String?=null
//
//
//    override fun getBindingVariable(): Int {
//        return BR.viewModel
//    }
//
//    override fun getLayoutId(): Int {
//        return R.layout.admin_product_class_layout
//    }
//
//    override fun getViewModel(): ProductClassViewModel {
//        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassViewModel::class.java)
//        return mViewModel as ProductClassViewModel
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        mViewDataBinding = getViewDataBinding()
//
//        setUp()
//        setUpEditor()
//    }
//
//    private fun setUp(){
//        adapter = ProductClassAdapter()
//        adapter?.listener = object : BaseClickListener<String> {
//            override fun onItemClick(item: String) {
//                lastItem = item
//                mViewModel?.productClassName?.set(item)
//            }
//
//        }
//        rvProductClass.itemAnimator.changeDuration = 0
//        rvProductClass.layoutManager = GridLayoutManager(context, 2)
//        rvProductClass.adapter = adapter
//        mViewModel?.getProductClassItems()?.observe(this, Observer { adapter?.setItems(it!!) })
//    }
//
//    private fun setUpEditor(){
//        btnEdit.setOnClickListener {
//            if(btnEdit.text == getString(R.string.edit))
//                mViewModel!!.isEditable.set(true)
//            //else netWorkRequest
//        }
//        btnDelete.setOnClickListener {
//            if(btnDelete.text == getString(R.string.cancel)) {
//                mViewModel?.isEditable?.set(false)
//                mViewModel?.productClassName?.set("")
//                mViewModel?.productClassName?.set(lastItem)
//            }//delete request
//        }
//    }
}