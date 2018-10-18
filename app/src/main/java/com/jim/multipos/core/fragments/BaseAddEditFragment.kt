package com.jim.multipos.core.fragments

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.jim.multipos.R
import com.jim.multipos.core.BaseViewModel
import kotlinx.android.synthetic.main.base_add_edit_fragment.*
import java.io.Serializable
import java.lang.Exception

abstract class BaseAddEditFragment<V: ViewDataBinding, T: BaseViewModel>: BaseFragment<V, T>() {

    var mode: AddEditModes = AddEditModes.ADD
        set(value) {
            field = value
            changeMode()
        }
    var isNewAdd = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LayoutInflater.from(context).inflate(getContentLayoutId(), flAddEditContent, true)
        if (savedInstanceState != null) {
            mode = savedInstanceState.getSerializable("mode") as AddEditModes
            isNewAdd = savedInstanceState.getSerializable("isNewAdd") as Boolean
        }



        btnLeft.setOnClickListener {
            if (mode == AddEditModes.ADD) {
                if (isNewAdd) {
                    newAddModeCanceled()
                } else {
                    editModeCanceled()
                }
            } else {
                if (!isNewAdd) {
                    delete()
                } else {
                    throw Exception("Deleting item is not selected!")
                }
            }
        }

        btnRight.setOnClickListener {
            if (mode == AddEditModes.ADD) {
                if (isNewAdd) {
                    newItemAddClicked()
                } else {
                    editItemSaveClicked()
                }
            } else {
                mode = AddEditModes.ADD
            }
        }
        initObservers()
    }

    abstract fun initObservers()


    internal open fun <M: Serializable> setModel(mModel: M?) {
        isNewAdd = mModel == null
        mode = if (isNewAdd) {
            AddEditModes.ADD
        } else {
            AddEditModes.EDIT
        }
    }

    override fun getLayoutId(): Int = R.layout.base_add_edit_fragment

    abstract fun getContentLayoutId(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("mode", mode)
        outState.putBoolean("isNewAdd", isNewAdd)
    }

    private fun changeMode() {
        if (mode == AddEditModes.ADD) {
            btnLeft.text = getString(R.string.cancel)
            btnRight.text = getString(R.string.save)
            onAddMode()
        } else {
            btnLeft.text = getString(R.string.delete)
            btnRight.text = getString(R.string.edit)
            onEditMode()
        }
    }

    internal open fun onEditMode() {}
    internal open fun onAddMode() {}
    internal open fun newItemAddClicked() {}
    internal open fun editItemSaveClicked() {}
    internal open fun newAddModeCanceled() {}
    internal open fun editModeCanceled() {}
    internal open fun delete() {}

}

enum class AddEditModes : Serializable {
    ADD, EDIT
}