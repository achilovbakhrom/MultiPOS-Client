package com.jim.multipos.core.fragments.baseAddEditFragment

import android.os.Bundle
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
abstract class BaseAddEditViewModel<M: Serializable>(dataManager: DataManager): BaseViewModel(dataManager) {

    var item: M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            item = savedInstanceState.getSerializable("item") as? M
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("item", item)
    }
}