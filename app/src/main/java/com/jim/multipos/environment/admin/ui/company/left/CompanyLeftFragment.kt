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
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.provideViewHolder
import com.jim.multipos.databinding.CompanyLeftFragmentBinding
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassViewHolder
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.single_list_fragment.*
import javax.inject.Inject

@Suppress("unchecked_cast")
class CompanyLeftFragment: SingleListFragment<CompanyDTO, CompanyLeftFragmentBinding, CompanyLeftViewModel>() {

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
                (rvSingle as MPRecyclerView<CompanyDTO>).addItems(it!!)
            }
            empty = temp && (rvSingle as MPRecyclerView<ProductClass>).itemsCount == 0
            rvSingle.loadMoreComplete()
            rvSingle.refreshComplete()
            rvSingle.stopLoading = it?.isEmpty() ?: false
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel?.page?.get()!!)
    }


    override fun initRV() {
        (rvSingle as MPRecyclerView<CompanyDTO>).viewHolder = provideViewHolder<CompanyDTO, CompanyViewHolder>(context!!)
        rvSingle.layoutManager = GridLayoutManager(context!!, 2)
        rvSingle.listener = object : MPRecyclerView.OnLoadMoreListener {
            override fun onLoadMore(recyclerView: RecyclerView) {
                mViewModel?.loadMore()
            }
            override fun onRefresh(recyclerView: RecyclerView) {
                mViewModel?.refresh()
            }
        }

        (rvSingle as MPRecyclerView<CompanyDTO>).itemSelectionListener = object : BaseActions<CompanyDTO> {

            override fun onItemClick(item: CompanyDTO?, position: Int) {
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_SELECTED.operation, item)
            }

            override fun onItemLongClick(item: CompanyDTO?, position: Int) {

            }

        }
    }

    override fun buttonAction() {
    }

    override fun emptyText(): String {
        return getString(R.string.add_company)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): CompanyLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyLeftViewModel::class.java)
        return mViewModel as CompanyLeftViewModel
    }

}

class CompanyViewHolder(itemView: View): BaseViewHolder<CompanyDTO>(itemView) {

    private val productClassName = itemView.findViewById<TextView>(R.id.tvProductClassName)
    private val productClassDescription = itemView.findViewById<TextView>(R.id.tvProductClassDescription)

    override fun onBind(item: CompanyDTO?, position: Int, isSelected: Boolean) {
//        productClassName.text = item?.name
//        productClassDescription.text = item?.des
    }

    @SuppressLint("InflateParams")
    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<CompanyDTO> {
        val view = LayoutInflater.from(context).inflate(R.layout.product_class_list_item, parent, false)
        return CompanyViewHolder(view)
    }

}