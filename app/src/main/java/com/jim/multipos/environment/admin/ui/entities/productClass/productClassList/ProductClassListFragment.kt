package com.jim.multipos.environment.admin.ui.entities.productClass.productClassList

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.fragments.DoubleHorizontalFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.SelectionModes
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.provideViewHolder
import com.jim.multipos.databinding.ProductClassListFragmentBinding
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.mp_recycler_view.*
import kotlinx.android.synthetic.main.single_list_fragment.*
import java.io.Serializable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProductClassListFragment: SingleListFragment<
        ProductClass,
        ProductClassListFragmentBinding,
        ProductClassListViewModel
        >(), Notifiable {

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
                (rvSingle as MPRecyclerView<ProductClass>).setItems(it!!)
            }
            empty = temp
            rvSingle.loadMoreComplete()
            rvSingle.refreshComplete()
        })
        mViewModel?.isLoading?.observe(this, Observer {
            isLoading = it ?: false
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel?.page?.get()!!)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): ProductClassListViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassListViewModel::class.java)
        return mViewModel as ProductClassListViewModel
    }

    override fun initRV() {
        (rvSingle as MPRecyclerView<ProductClass>).viewHolder = provideViewHolder<ProductClass, ProductClassViewHolder>(context!!)
        rvSingle.layoutManager = GridLayoutManager(context!!, 2)

        rvSingle.listener = object : MPRecyclerView.OnLoadMoreListener {
            override fun onLoadMore(recyclerView: RecyclerView) {
                mViewModel?.loadMore()

            }
            override fun onRefresh(recyclerView: RecyclerView) {
                rvSingle.unselect()
                mViewModel?.refresh()
            }
        }
        rvSingle.selectionMode = SelectionModes.SINGLE
        rvSingle.itemSelectionListener = object : BaseActions<Serializable> {
            override fun onItemClick(item: Serializable?, position: Int) {
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_SELECTED.operation, item as? ProductClass)
            }
            override fun onItemLongClick(item: Serializable?, position: Int) {}

        }
    }

    override fun buttonAction() {
        rvSingle.unselect()
        sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.ADD_NEW_ITEM.operation)
    }
    override fun emptyText(): String = getString(R.string.add_product_class_nl)

    override fun notify(action: String?, data: Any?) {
        when(action) {
            FragmentCommunicationOperations.CANCEL.operation -> { rvSingle.unselect() }
            FragmentCommunicationOperations.REFRESH_LIST.operation -> {
                srSwipeRefresh.isRefreshing = true
                mViewModel?.refresh()
            }
            FragmentCommunicationOperations.ADD_NEW_ITEM.operation -> {
                (rvSingle as MPRecyclerView<ProductClass>).addItemAt(data as ProductClass, 0)
            }
            FragmentCommunicationOperations.ITEM_SELECTED.operation -> {}
        }
    }

}

class ProductClassViewHolder(itemView: View): BaseViewHolder<ProductClass>(itemView) {

    private val productClassName = itemView.findViewById<TextView>(R.id.tvProductClassName)
    private val productClassDescription = itemView.findViewById<TextView>(R.id.tvProductClassDescription)
    private val productClassItemBg = itemView.findViewById<CardView>(R.id.productClassItemBg)

    override fun onBind(item: ProductClass?, position: Int, isSelected: Boolean) {
        productClassName.text = item?.name
        productClassDescription.text = item?.description
        val context = productClassName.context
        if (isSelected) {
            productClassItemBg.setCardBackgroundColor(context.getColor(R.color.colorPrimary))
        } else {
            productClassItemBg.setCardBackgroundColor(Color.WHITE)
        }
    }

    @SuppressLint("InflateParams")
    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<ProductClass> {
        val view = LayoutInflater.from(context).inflate(R.layout.product_class_list_item, parent, false)
        return ProductClassViewHolder(view)
    }
}