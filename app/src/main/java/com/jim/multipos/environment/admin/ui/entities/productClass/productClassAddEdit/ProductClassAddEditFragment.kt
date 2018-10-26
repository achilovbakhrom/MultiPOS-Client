package com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDialog
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.LEFT_FRAGMENT_TAG
import com.jim.multipos.core.fragments.baseAddEditFragment.AddEditModeButtonsClickListener
import com.jim.multipos.core.fragments.baseAddEditFragment.AddEditModes
import com.jim.multipos.core.fragments.baseAddEditFragment.BaseAddEditFragment
import com.jim.multipos.customView.MpEditText
import com.jim.multipos.databinding.ProductClassListFragmentBinding
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.base_add_edit_fragment.*
import kotlinx.android.synthetic.main.product_class_add_edit_fragment.*
import javax.inject.Inject

class ProductClassAddEditFragment:
        BaseAddEditFragment<ProductClass, ProductClassListFragmentBinding, ProductClassAddEditViewModel>(),
        AddEditModeButtonsClickListener, Notifiable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mode = AddEditModes.EMPTY
        listener = this

//        flAddEditProgress.findViewById<MpEditText>(R.id.etProductClassName).addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(p0: Editable?) {
//                flAddEditProgress.findViewById<MpEditText>(R.id.etProductClassName).error = ""
//            }
//        })
    }

    override fun getEmptyString(): String = getString(R.string.select_to_view_info, "Product Class")
    override fun infoModeLayoutId(): Int = R.layout.product_class_info_fragment
    override fun addEditModeLayoutId(): Int = R.layout.product_class_add_edit_fragment
    override fun getBindingVariable(): Int = BR.viewModel
    override fun getViewModel(): ProductClassAddEditViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductClassAddEditViewModel::class.java)
        return mViewModel as ProductClassAddEditViewModel
    }

    override fun initObservers() {

        mViewModel?.isLoading?.observe(this, Observer {
            isLoading = it ?: false
        })

        mViewModel?.savedEvent?.observe(this, Observer {
            mode = AddEditModes.EMPTY
        })

        // adding item
        mViewModel?.addedItem?.observe(this, Observer {
            sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.ADD_NEW_ITEM.operation, it)
        })

        mViewModel?.updatedEvent?.observe(this, Observer {
            mode = AddEditModes.EMPTY
            sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.REFRESH_LIST.operation, null)
        })

        mViewModel?.nameErrorEvent?.observe(this, Observer {
            flAddEditContent.findViewById<MpEditText>(R.id.etProductClassName).error = it ?: getString(R.string.unknown_error)
        })

    }

    override fun fillAddEditMode(item: ProductClass?) {}
    override fun fillInfoMode(item: ProductClass) {
        flAddEditContent.findViewById<TextView>(R.id.tvProductClassName).text = item.name
        flAddEditContent.findViewById<TextView>(R.id.tvProductClassActive).text = if(item.active) { getString(R.string.yes) } else { getString(R.string.no) }
        flAddEditContent.findViewById<TextView>(R.id.tvProductClassDescription).text = item.description
    }

    /**
     *  Add edit mode buttons click listener
     */
    override fun onDeleteClick() {
//        val dialog = AppCompatDialog(context)
//        dialog.setContentView(R.layout.delete_item_dialog)
//        dialog.show()
        val dialog = AlertDialog.Builder(context!!, R.style.DeleteDialog)
        dialog.setView(R.layout.delete_item_dialog)
        dialog.create().window.setLayout(WRAP_CONTENT, WRAP_CONTENT)
        dialog.show()
    }
    override fun onEditClick() {}
    override fun onSaveClick() {
        val productClassName = flAddEditContent.findViewById<EditText>(R.id.etProductClassName).text.toString()
        val productClassActive = flAddEditContent.findViewById<Switch>(R.id.swProductClassActive).isChecked
        val productClassDescription = flAddEditContent.findViewById<EditText>(R.id.etProductClassDescription).text.toString()

        val productClass = ProductClass()
        productClass.name = productClassName
        productClass.active = productClassActive
        productClass.description = productClassDescription
        this.mViewModel?.saveEvent(productClass)
    }
    override fun onCancelAddEditClick() {
        sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.CANCEL.operation, null)
        mode = AddEditModes.EMPTY
    }

    override fun notify(action: String?, data: Any?) {
        when (action) {
            FragmentCommunicationOperations.ADD_NEW_ITEM.operation -> {
                item = null
                mode = AddEditModes.ADD_EDIT
            }
            FragmentCommunicationOperations.ITEM_SELECTED.operation -> {
                item = data as? ProductClass
                mode = AddEditModes.INFO
            }
        }
    }


}
