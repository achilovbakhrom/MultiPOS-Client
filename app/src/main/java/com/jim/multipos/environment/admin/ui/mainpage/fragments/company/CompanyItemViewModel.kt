package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import android.databinding.ObservableField
import android.databinding.ObservableBoolean

class CompanyItemViewModel(var text: String) {

    val isClicked = ObservableBoolean(false)

    var title: ObservableField<String>?=null

    init {
        title = ObservableField(text)
    }

}