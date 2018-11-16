package com.jim.multipos.environment.admin.ui.company.right.show.contactPerson

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
import com.jim.multipos.databinding.CompanyPersonViewListLayoutBinding
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import com.jim.multipos.environment.admin.model.CompanyContactPerson
import com.jim.multipos.environment.admin.model.ContactOrBankAddEdit
import com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson.CompanyContactViewHolder
import com.jim.multipos.utils.CompanyEditCreate
import com.jim.multipos.utils.FragmentCommunicationOperations
import com.jim.multipos.utils.Gender
import kotlinx.android.synthetic.main.company_contact_person_detail_view_layout.*
import kotlinx.android.synthetic.main.company_contact_person_fragment.*
import kotlinx.android.synthetic.main.company_person_view_list_layout.*
import java.util.*
import javax.inject.Inject

class CompanyShowContactPersonFragment: BaseFragment<CompanyPersonViewListLayoutBinding, CompanyShowContactPersonViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    var lastPosition = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            val json = arguments?.getString("contactPerson", "")
            val contacts = Gson().fromJson(json, Array<CompanyContactPerson>::class.java)
            mViewModel?.contactInformation = CompanyContactInformation(contacts.toCollection(ArrayList()))
        }

        mViewModel?.onViewCreated()

        LayoutInflater
                .from(context)
                .inflate(R.layout.company_contact_person_detail_view_layout, flContactPersonInfo, true)

        rvCompanyShowContactPersonRV.setRVBackgroundColor(ContextCompat.getColor(context!!, R.color.colorLightGray))
        initRV()
        initUI()
        initObservables()
    }


    private fun initUI() {
        flContactPersonInfo.findViewById<Button>(R.id.btnBack).setOnClickListener {
            flContactPersonInfo.visibility = GONE
            rvCompanyShowContactPersonRV.visibility = VISIBLE
            fabContactPerson.visibility = VISIBLE
        }

        fabContactPerson.setOnClickListener {
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, ContactOrBankAddEdit(CompanyEditCreate.ADD_CONTACT.operation, ""))
        }

        flContactPersonInfo.findViewById<Button>(R.id.btnEdit).setOnClickListener {
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation,
                    ContactOrBankAddEdit(CompanyEditCreate.EDIT_CONTACT.operation, mViewModel?.contactInformation?.list?.get(lastPosition)!!, lastPosition))
        }

        flContactPersonInfo.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            //todo add dialog before deleting
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation,
                    ContactOrBankAddEdit(CompanyEditCreate.DELETE_CONTACT.operation, Any(), lastPosition))
        }

    }

    private fun initRV() {
        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).viewHolder = provideViewHolder<CompanyContactPerson, CompanyContactViewHolder>(context!!)
        rvCompanyShowContactPersonRV.layoutManager = LinearLayoutManager(context!!)
        rvCompanyShowContactPersonRV.listener = object : MPRecyclerView.OnLoadMoreListener {
            override fun onLoadMore(recyclerView: RecyclerView) {

            }

            override fun onRefresh(recyclerView: RecyclerView) {

            }

        }

        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).itemSelectionListener = object : BaseActions<CompanyContactPerson>{
            override fun onItemClick(item: CompanyContactPerson?, position: Int) {
                populate(item!!)
                lastPosition = position
            }

            override fun onItemSelected(items: List<CompanyContactPerson>?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemLongClick(item: CompanyContactPerson?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).stopLoading = true
        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).addItems(mViewModel?.contactInformation?.list!!)

    }

    private fun initObservables() {
        mViewModel?.fillContactPerson?.observe(this, Observer {
            rlContactPersonEmpty.visibility = GONE
            rvCompanyShowContactPersonRV.visibility = VISIBLE
        })
    }

    fun setItem(model: CompanyContactPerson){
        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).setItem(model, lastPosition)
        mViewModel?.contactInformation?.list?.set(lastPosition, model)
    }

    fun addItem(model: CompanyContactPerson){
        if(rvCompanyShowContactPersonRV.visibility == GONE){
            rvCompanyShowContactPersonRV.visibility = VISIBLE
            fabContactPerson.visibility = VISIBLE
            rlContactPersonEmpty.visibility = GONE
        }
        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).addItem(model)
        mViewModel?.contactInformation?.list?.add(model)
    }

    fun populateUI(item: CompanyContactPerson){
        tvContactPersonFN.text = item.firstName
    }

    fun deleteItem(){
        (rvCompanyShowContactPersonRV as MPRecyclerView<CompanyContactPerson>).removeItem(lastPosition)
        if(rvCompanyShowContactPersonRV?.itemsCount!!>0) {
            rvCompanyShowContactPersonRV.visibility = VISIBLE
            flContactPersonInfo.visibility = GONE
        }else{
            rvCompanyShowContactPersonRV.visibility = GONE
            flContactPersonInfo.visibility = GONE
            rlContactPersonEmpty.visibility = VISIBLE
        }
    }

    private fun populate(item: CompanyContactPerson){
        flContactPersonInfo.visibility = VISIBLE
        rvCompanyShowContactPersonRV.visibility = GONE
        fabContactPerson.visibility = GONE
        flContactPersonInfo.findViewById<TextView>(R.id.tvContactPersonFN).text = item.firstName
        flContactPersonInfo.findViewById<TextView>(R.id.tvContactPersonLN).text = item.lastName
        flContactPersonInfo.findViewById<TextView>(R.id.tvContactPersonPhone).text = item.work_PhoneNo
        flContactPersonInfo.findViewById<TextView>(R.id.tvContactPersonPosition).text = item.positionName
        flContactPersonInfo.findViewById<TextView>(R.id.tvContactPersonGender).text = Gender.getByPosition(item.gender!!)
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_person_view_list_layout
    override fun getViewModel(): CompanyShowContactPersonViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowContactPersonViewModel::class.java]
        return mViewModel as CompanyShowContactPersonViewModel
    }


}

class CompanyContactPersonShowViewHolder(itemView: View): BaseViewHolder<CompanyContactPerson>(itemView){

    private val ivContactPerson = itemView.findViewById<ImageView>(R.id.ivContactPerson)
    private val tvContactPersonName = itemView.findViewById<TextView>(R.id.tvContactPersonName)
    private val tvContactPersonPosition = itemView.findViewById<TextView>(R.id.tvContactPersonPosition)
    private val tvContactPersonPhone = itemView.findViewById<TextView>(R.id.tvContactPersonPhone)
    private val tvContactPersonEmail = itemView.findViewById<TextView>(R.id.tvContactPersonEmail)

    override fun onBind(item: CompanyContactPerson?, position: Int, isSelected: Boolean, mode: SelectionMode) {
        if(mode == SelectionMode.SINGLE){
            tvContactPersonName.text = item?.firstName?.plus(item?.lastName)
            tvContactPersonPosition.text = item?.positionId
            tvContactPersonPhone.text = item?.work_PhoneNo
            tvContactPersonEmail.text = item?.email
        }

    }

    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<CompanyContactPerson> {
        val view = LayoutInflater.from(context).inflate(R.layout.company_contact_person_show_item_list, parent, false)
        return CompanyContactPersonShowViewHolder(view)
    }

}