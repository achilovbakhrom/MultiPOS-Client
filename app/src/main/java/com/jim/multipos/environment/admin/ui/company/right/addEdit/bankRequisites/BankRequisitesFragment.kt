package com.jim.multipos.environment.admin.ui.company.right.addEdit.bankRequisites

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.customView.recyclerView.MPRecyclerView
import com.jim.multipos.customView.recyclerView.adapter.BaseViewHolder
import com.jim.multipos.customView.recyclerView.adapter.SelectionMode
import com.jim.multipos.customView.recyclerView.provideViewHolder
import com.jim.multipos.databinding.CompanyBankRequisitesListFragmentBinding
import com.jim.multipos.environment.admin.model.CompanyRequisiteInformation
import com.jim.multipos.environment.admin.model.Requisite
import com.jim.multipos.environment.admin.model.RequisiteItem
import com.jim.multipos.utils.FragmentCommunicationOperations
import fisk.chipcloud.ChipCloud
import fisk.chipcloud.ChipCloudConfig
import kotlinx.android.synthetic.main.company_bank_requisite_add_edit_layout.*
import kotlinx.android.synthetic.main.company_bank_requisites_list_fragment.*
import javax.inject.Inject


class BankRequisitesFragment: BaseFragment<CompanyBankRequisitesListFragmentBinding, BankRequisitesViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var editMode = false
    var pos = 0
    var chipCloud: ChipCloud?=null
    var chipcloudConfig: ChipCloudConfig?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments!=null){
            val json = arguments?.getString("bankRequisite", "")
            val requisites = Gson().fromJson(json, Array<Requisite>::class.java)
            mViewModel?.companyRequisiteInfo = CompanyRequisiteInformation(requisites.toMutableList())
        }

        mViewModel?.onViewCreated()

        LayoutInflater.from(context).inflate(R.layout.company_bank_requisite_add_edit_layout, flEditor, true)
        rvRequisites?.setRVBackgroundColor(ContextCompat.getColor(context!!, R.color.colorLightGray))

        initObservers()
        initUI()
        initRV()
    }

    private fun initRV() {
        (rvRequisites as MPRecyclerView<Requisite>).viewHolder = provideViewHolder<Requisite, BankRequisiteViewHolder>(context!!)
        rvRequisites.layoutManager = LinearLayoutManager(context!!)
        (rvRequisites as MPRecyclerView<Requisite>).addItems(mViewModel?.companyRequisiteInfo?.list!!)
        rvRequisites.stopLoading = true

        (rvRequisites as MPRecyclerView<Requisite>).itemSelectionListener = object : BaseActions<Requisite>{
            override fun onItemClick(item: Requisite?, position: Int) {
                pos = position
                editMode = true
                populateEditor(item!!)
            }

            override fun onItemSelected(items: List<Requisite>?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemLongClick(item: Requisite?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

    private fun initUI() {

        chipcloudConfig = ChipCloudConfig()
                .selectMode(ChipCloud.SelectMode.multi)
                .checkedChipColor(Color.parseColor("#ddaa00"))
                .checkedTextColor(Color.parseColor("#ffffff"))
                .uncheckedChipColor(Color.parseColor("#e0e0e0"))
                .uncheckedTextColor(Color.parseColor("#000000"))
                .showClose(Color.parseColor("#a6a6a6"), 500)

        chipCloud = ChipCloud(context, flexBox, chipcloudConfig)

        btnSave.setOnClickListener {
            mViewModel?.companyRequisiteInfo?.onNext = true
            mViewModel?.deliverDataToMainClass()
        }

        btnCancelRequisite.setOnClickListener {
            clearUIItems()
        }


        fabAdd.setOnClickListener {
            llRv.visibility = GONE
            flEditor.visibility = VISIBLE
            editMode = false
        }

        btnSaveRequisite.setOnClickListener {
            llRv.visibility = VISIBLE
            rvRequisites.visibility = VISIBLE
            rlRequisitesEmptyList.visibility = GONE
            flEditor.visibility = GONE


            val requisite = Requisite(etRequisiteName.text.toString(),
                    mViewModel?.chips?.toMutableList(), 1,
                    etDescription.text.toString())

            //chip returns 0
            if(editMode) {
                mViewModel?.editRequisite(requisite, pos)
                (rvRequisites as MPRecyclerView<Requisite>).setItem(requisite, pos)
            }else{
                mViewModel?.addRequisite(requisite)
                (rvRequisites as MPRecyclerView<Requisite>).addItem(requisite)
            }
            etRequisiteName.setText("")
            etDescription.setText("")
            flexBox.removeAllViews()
            mViewModel?.chips?.clear()
        }


        btnAddChip.setOnClickListener {
            chipCloud?.addChip(etChipName.text.toString())
            mViewModel?.chips?.add(RequisiteItem(etChipName.text.toString(), etChipValue.text.toString()))
            etChipName.setText("")
            etChipValue.setText("")
        }

    }

    private fun initObservers() {
        mViewModel?.setBankRequisites?.observe(this, Observer {
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, mViewModel?.companyRequisiteInfo)
        })

        mViewModel?.fillBankRequisite?.observe(this, Observer {
            rvRequisites.visibility = VISIBLE
            rlRequisitesEmptyList.visibility = GONE
        })
    }

    private fun clearUIItems(){
        etRequisiteName.setText("")
        etDescription.setText("")
        etChipName.setText("")
        etChipValue.setText("")
        mViewModel?.chips?.clear()
        flexBox.removeAllViews()
        flEditor.visibility = GONE
        llRv.visibility = VISIBLE
        if(mViewModel?.companyRequisiteInfo?.list?.size!! > 0){
            rvRequisites.visibility = VISIBLE
            rlRequisitesEmptyList.visibility = GONE
        }else{
            rvRequisites.visibility = GONE
            rlRequisitesEmptyList.visibility = VISIBLE
        }
    }

    fun populateEditor(requisite: Requisite){
        llRv.visibility = GONE
        flEditor.visibility = VISIBLE
        etRequisiteName.setText(requisite.requisiteName)
        etDescription.setText(requisite.requisiteDescription)
        for (chip in requisite.requisiteItems!!){
            chipCloud?.addChip(chip.requisiteItemName)
            mViewModel?.chips?.add(chip)
        }
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_bank_requisites_list_fragment
    override fun getViewModel(): BankRequisitesViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(BankRequisitesViewModel::class.java)
        return mViewModel as BankRequisitesViewModel
    }

    fun deliverDataToMainClass() {
        mViewModel?.deliverDataToMainClass()
    }
}

class BankRequisiteViewHolder(itemView: View): BaseViewHolder<Requisite>(itemView){

    val tvRequisiteName = itemView.findViewById<TextView>(R.id.tvRequisiteName)
    val tvRequisiteValue = itemView.findViewById<TextView>(R.id.tvRequisiteValue)

    override fun onBind(item: Requisite?, position: Int, isSelected: Boolean, mode: SelectionMode) {
        if(mode == SelectionMode.SINGLE){
            tvRequisiteName.text = item?.requisiteName
            tvRequisiteValue.text = item?.requisiteDescription
        }
    }

    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<Requisite> {
        val view = LayoutInflater.from(context).inflate(R.layout.company_bank_requisite_item_list, parent, false)
        return BankRequisiteViewHolder(view)
    }

}