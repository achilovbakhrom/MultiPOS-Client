package com.jim.multipos.environment.admin.ui.company.left

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.adapter.SelectionMode
import com.jim.multipos.customView.recyclerView.provideViewHolder
import com.jim.multipos.databinding.CompanyLeftFragmentBinding
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassViewHolder
import com.jim.multipos.utils.FragmentCommunicationOperations
import com.jim.multipos.utils.PrefsManager
import kotlinx.android.synthetic.main.single_list_fragment.*
import javax.inject.Inject

@Suppress("unchecked_cast")
class CompanyLeftFragment: SingleListFragment<CompanyDTO, CompanyLeftFragmentBinding, CompanyLeftViewModel>(), Notifiable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsManager: PrefsManager

    var lastPos =-1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState?.getInt("page") != null) {
            mViewModel?.page?.set(savedInstanceState.getInt("page"))
        }

        mViewModel?.onViewCreated()

        initObservables()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("page", mViewModel?.page?.get()!!)
    }


    override fun initRV() {
        (rvSingle as MPRecyclerView<CompanyDTO>).viewHolder = provideViewHolder<CompanyDTO, CompanyViewHolder>(context!!)
        rvSingle.layoutManager = LinearLayoutManager(context!!)
        rvSingle.listener = object : MPRecyclerView.OnLoadMoreListener {
            override fun onLoadMore(recyclerView: RecyclerView) {
                mViewModel?.loadMore()
            }
            override fun onRefresh(recyclerView: RecyclerView) {
                mViewModel?.refresh()
            }
        }

        (rvSingle as MPRecyclerView<CompanyDTO>).itemSelectionListener = object : BaseActions<CompanyDTO> {

            override fun onItemSelected(items: List<CompanyDTO>?, position: Int) {

            }

            override fun onItemClick(item: CompanyDTO?, position: Int) {
                lastPos = position
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_SELECTED.operation, item)
            }

            override fun onItemLongClick(item: CompanyDTO?, position: Int) {

            }

        }
    }

    private fun initObservables(){
        mViewModel?.data?.observe(this, Observer {
            val temp= it == null || it.isEmpty()
            if (!temp) {
                (rvSingle as MPRecyclerView<CompanyDTO>).addItems(it!!)
            }
            empty = temp && (rvSingle as MPRecyclerView<ProductClass>).itemsCount == 0
            rvSingle.loadMoreComplete()
            rvSingle.refreshComplete()
//            rvSingle.stopLoading = it?.isEmpty() ?: false
            rvSingle.stopLoading = true
        })

        mViewModel?.errorMessage?.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun buttonAction() {
        rvSingle.unselect()
        sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.ADD_NEW_ITEM.operation, null)
    }

    override fun emptyText(): String {
        return getString(R.string.add_company)
    }


    override fun notify(action: String?, data: Any?) {
        when(action){
            FragmentCommunicationOperations.ITEM_ADDED.operation->{
                (rvSingle as MPRecyclerView<CompanyDTO>).addItem(data as CompanyDTO)
            }
            FragmentCommunicationOperations.ITEM_EDITED.operation -> {
                if(lastPos!=-1)
                    (rvSingle as MPRecyclerView<CompanyDTO>).setItem(data as CompanyDTO, lastPos)
            }
        }
    }


    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): CompanyLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyLeftViewModel::class.java)
        return mViewModel as CompanyLeftViewModel
    }


}

class CompanyViewHolder(itemView: View): BaseViewHolder<CompanyDTO>(itemView) {

    private val star = itemView.findViewById<ImageView>(R.id.ivCompanyItemStar)
    private val avatar = itemView.findViewById<ImageView>(R.id.ivCompanyAvatar)
    private val companyName = itemView.findViewById<TextView>(R.id.tvCompanyItemName)
    private val companyOccupation = itemView.findViewById<TextView>(R.id.tvCompanyItemOccupation)
    private val companyDescription = itemView.findViewById<TextView>(R.id.tvCompanyItemDescription)
    private val cardView = itemView.findViewById<CardView>(R.id.cvCompanyItemParent)

    var context = itemView.context

    override fun onBind(item: CompanyDTO?, position: Int, isSelected: Boolean, mode: SelectionMode) {

        if (mode == SelectionMode.SINGLE) {
            val company = item?.company
            companyName.text = company?.name
            companyOccupation.text = company?.occupation
            companyDescription.text = company?.description

            if (isSelected) {
                cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.edit_blue))
            } else {
                cardView.setCardBackgroundColor(ContextCompat.getColor(context!!, R.color.colorWhite))
            }
        } else {

        }

    }

    @SuppressLint("InflateParams")
    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<CompanyDTO> {
        val view = LayoutInflater.from(context).inflate(R.layout.company_left_fragment_list_item, parent, false)
        return CompanyViewHolder(view)
    }
}