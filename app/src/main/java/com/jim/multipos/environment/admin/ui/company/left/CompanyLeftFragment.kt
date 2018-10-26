package com.jim.multipos.environment.admin.ui.company.left

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.provideViewHolder
import com.jim.multipos.databinding.CompanyLeftFragmentBinding
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassViewHolder
import kotlinx.android.synthetic.main.single_list_fragment.*
import javax.inject.Inject

class CompanyLeftFragment: SingleListFragment<Company, CompanyLeftFragmentBinding, CompanyLeftViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState?.getInt("page") != null) {
            mViewModel?.page?.set(savedInstanceState.getInt("page"))
        }

        mViewModel?.onViewCreated()

        mViewModel?.data?.observe(this, Observer {
            val temp= it == null || it.isEmpty()
            if (!temp) {
                (rvSingle as MPRecyclerView<Company>).setItems(it!!)
            }
            empty = temp
            rvSingle.loadMoreComplete()
            rvSingle.refreshComplete()
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel?.page?.get()!!)
    }


    override fun initRV() {
        (rvSingle as MPRecyclerView<Company>).viewHolder = provideViewHolder<Company, CompanyViewHolder>(context!!)
        rvSingle.layoutManager = GridLayoutManager(context!!, 2)
        rvSingle.listener = object : MPRecyclerView.OnLoadMoreListener {
            override fun onLoadMore(recyclerView: RecyclerView) {
                mViewModel?.loadMore()

            }
            override fun onRefresh(recyclerView: RecyclerView) {
                mViewModel?.refresh()
            }
        }
    }

    override fun buttonAction() {
    }

    override fun emptyText(): String {
        return "Add Company"
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): CompanyLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyLeftViewModel::class.java)
        return mViewModel as CompanyLeftViewModel
    }

}

class CompanyViewHolder(itemView: View): BaseViewHolder<Company>(itemView) {

    private val productClassName = itemView.findViewById<TextView>(R.id.tvProductClassName)
    private val productClassDescription = itemView.findViewById<TextView>(R.id.tvProductClassDescription)

    override fun onBind(item: Company?, position: Int, isSelected: Boolean) {
        productClassName.text = item?.name
        productClassDescription.text = item?.description
    }

    @SuppressLint("InflateParams")
    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<Company> {
        val view = LayoutInflater.from(context).inflate(R.layout.product_class_list_item, parent, false)
        return CompanyViewHolder(view)
    }

}