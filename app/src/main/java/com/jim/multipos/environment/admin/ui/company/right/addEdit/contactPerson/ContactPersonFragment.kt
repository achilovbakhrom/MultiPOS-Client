package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

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
import com.jim.multipos.databinding.CompanyContactPersonFragmentBinding
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import com.jim.multipos.environment.admin.model.CompanyContactPerson
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_contact_person_add_edit_layout.*
import kotlinx.android.synthetic.main.company_contact_person_fragment.*
import kotlinx.android.synthetic.main.company_person_view_list_layout.*
import java.util.*
import javax.inject.Inject

class ContactPersonFragment: BaseFragment<CompanyContactPersonFragmentBinding, ContactPersonViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var editMode: Boolean = false
    var pos: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //todo get contact list as json parse and populate
        if (arguments!=null){
            val json = arguments?.getString("contactPerson", "")
            val contacts = Gson().fromJson(json, Array<CompanyContactPerson>::class.java)
            mViewModel?.setContactInfo(CompanyContactInformation(contacts.toCollection(ArrayList())))
        }

        rvContactPerson?.setRVBackgroundColor(ContextCompat.getColor(context!!, R.color.colorLightGray))
        mViewModel?.onViewCreated()

        LayoutInflater
                .from(context)
                .inflate(R.layout.company_contact_person_add_edit_layout, flContactPerson, true)

        initRV()
        initUI()
        fbAddContact.setOnClickListener {
            llContactPersonAddEditListContent.visibility = GONE
            flContactPerson.visibility = VISIBLE
            editMode = false
            clearUIItems()
        }
        this.initObservers()

    }

    private fun initRV() {
        (rvContactPerson as MPRecyclerView<CompanyContactPerson>).viewHolder = provideViewHolder<CompanyContactPerson, CompanyContactViewHolder>(context!!)
        rvContactPerson?.layoutManager = LinearLayoutManager(context!!)
        rvContactPerson?.listener = object : MPRecyclerView.OnLoadMoreListener{
            override fun onLoadMore(recyclerView: RecyclerView) {

            }

            override fun onRefresh(recyclerView: RecyclerView) {

            }
        }

        (rvContactPerson as MPRecyclerView<CompanyContactPerson>).itemSelectionListener = object : BaseActions<CompanyContactPerson>{
            override fun onItemClick(item: CompanyContactPerson?, position: Int) {
                populate(item!!)
                pos = position
                editMode = true
            }

            override fun onItemSelected(items: List<CompanyContactPerson>?, position: Int) {

            }

            override fun onItemLongClick(item: CompanyContactPerson?, position: Int) {

            }

        }

        (rvContactPerson as MPRecyclerView<CompanyContactPerson>).addItems(mViewModel?.contactInformation?.list?.toList()!!)
        rvContactPerson?.stopLoading = true
    }

    private fun initObservers() {
        mViewModel?.setContactPerson?.observe(this, Observer {
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, mViewModel?.contactInformation)
        })

        mViewModel?.fillContactPerson?.observe(this, Observer {
            rvContactPerson.visibility = VISIBLE
            rlContactPersonEmptyList.visibility = GONE
        })
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_contact_person_fragment
    override fun getViewModel(): ContactPersonViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(ContactPersonViewModel::class.java)
        return mViewModel as ContactPersonViewModel
    }

    fun deliverDataToMainClass() {
        mViewModel?.deliverDataToMainClass()
    }


    private fun initUI() {
        btnContactPersonCancel.setOnClickListener {
            flContactPerson.visibility = GONE
            llContactPersonAddEditListContent.visibility = VISIBLE
        }

        mpGenderRadioButton.setList(mutableListOf("Male", "Female"))
        mpMaritalStatusRadioButton.setList(mutableListOf("Single", "Married", "Divorced", "Widowed"))

        btnNext.setOnClickListener {
            mViewModel?.contactInformation?.onNextAction = true
            mViewModel?.deliverDataToMainClass()
        }

        btnContactPersonSave.setOnClickListener {
            llContactPersonAddEditListContent.visibility = VISIBLE
            flContactPerson.visibility = GONE
            var model = CompanyContactPerson(
                    etPersonFirstName.text.toString(),
                    etContactPersonLastName.text.toString(),
                    "CEO",
                    "",
                    mpGenderRadioButton.position,
                    mpMaritalStatusRadioButton.position,
                    etContactPersonNationality.text.toString(),
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            )
            rlContactPersonEmptyList.visibility = GONE
            rvContactPerson.visibility = VISIBLE
            if(editMode){
                mViewModel?.editContactInfoItem(model, pos)
                (rvContactPerson as MPRecyclerView<CompanyContactPerson>).setItem(model, pos)
            }else {
                mViewModel?.addContactInfoItem(model)
                (rvContactPerson as MPRecyclerView<CompanyContactPerson>).addItem(model)
            }
        }
    }

    private fun populate(model: CompanyContactPerson){
        rlContactPersonEmptyList.visibility = GONE
        llContactPersonAddEditListContent.visibility = GONE
        flContactPerson.visibility = VISIBLE

        etPersonFirstName.setText(model.firstName)
        etContactPersonLastName.setText(model.lastName)
        mpGenderRadioButton.position = model.gender!!
        mpGenderRadioButton.setList(mutableListOf("Male", "Female"))
        etContactPersonNationality.setText(model.nationality)
    }

    private fun clearUIItems(){
        etPersonFirstName.setText("")
    }
}

class CompanyContactViewHolder(itemView: View): BaseViewHolder<CompanyContactPerson>(itemView){

    private val tvContactName = itemView.findViewById<TextView>(R.id.tvContactName)
    private val tvContactPosition = itemView.findViewById<TextView>(R.id.tvContactPosition)

    override fun onBind(item: CompanyContactPerson?, position: Int, isSelected: Boolean, mode: SelectionMode) {
        if(mode == SelectionMode.SINGLE){
            tvContactName.text = item?.firstName
            tvContactPosition.text = item?.positionId
        }
    }

    override fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<CompanyContactPerson> {
        val view = LayoutInflater.from(context).inflate(R.layout.company_contact_person_item_list, parent, false)
        return CompanyContactViewHolder(view)
    }

}