package com.jim.multipos.core.fragments.baseAddEditFragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.fragments.BaseFragment
import kotlinx.android.synthetic.main.base_add_edit_fragment.*
import kotlinx.android.synthetic.main.base_add_edit_layout.*
import kotlinx.android.synthetic.main.base_empty_layout.*
import kotlinx.android.synthetic.main.base_info_layout.*
import java.io.Serializable
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
abstract class BaseAddEditFragment<M: Serializable, V: ViewDataBinding, T: BaseViewModel>: BaseFragment<V, T>() {

    var mode: AddEditModes = AddEditModes.EMPTY
        set(value) {
            field = value
            changeMode()
        }

    var item: M? = null
        set(value) {
            (mViewModel as BaseAddEditViewModel<M>).item = value
            field = value
        }

    var listener: AddEditModeButtonsClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    abstract fun initObservers()
    override fun getLayoutId(): Int = R.layout.base_add_edit_fragment

    /**
     *  Empty mode layout bottom string
     */
    abstract fun getEmptyString(): String

    /**
     *  Info mode methods
     */
    abstract fun fillInfoMode(item: M)
    abstract fun infoModeLayoutId(): Int

    /**
     *  Add Edit mode methods
     */
    abstract fun fillAddEditMode(item: M?)
    abstract fun addEditModeLayoutId(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("mode", mode)
    }

    private fun changeMode() {
        when (mode) {
            AddEditModes.EMPTY -> setEmptyMode()
            AddEditModes.INFO -> setInfoMode()
            AddEditModes.ADD_EDIT -> setAddEditMode()
        }
    }

    private fun setEmptyMode() {
        LayoutInflater.from(context).inflate(R.layout.base_empty_layout, flAddEditContent, true)
        tvEmptyBottom.text = getEmptyString()
        item = null
    }

    private fun setInfoMode() {
        if (item != null) {
            LayoutInflater.from(context).inflate(R.layout.base_info_layout, flAddEditContent, true)
            LayoutInflater.from(context).inflate(infoModeLayoutId(), flInfoContent, true)
            fillInfoMode(item!!)

            btnEdit.setOnClickListener {
                listener?.onEditClick()
            }

            btnDelete.setOnClickListener {
                listener?.onDeleteClick()
            }
        } else {
            throw Exception("Item is not set!!!")
        }
    }

    private fun setAddEditMode() {
        fillAddEditMode(item)
        LayoutInflater.from(context).inflate(R.layout.base_add_edit_layout, flAddEditLayoutContent, true)

        btnCancel.setOnClickListener {
            listener?.onCancelAddEditClick()
        }

        btnSave.setOnClickListener {
            listener?.onSaveClick()
        }
    }
}

interface AddEditModeButtonsClickListener {
    fun onDeleteClick()
    fun onEditClick()
    fun onSaveClick()
    fun onCancelAddEditClick()
}

enum class AddEditModes : Serializable {
    EMPTY, INFO, ADD_EDIT
}