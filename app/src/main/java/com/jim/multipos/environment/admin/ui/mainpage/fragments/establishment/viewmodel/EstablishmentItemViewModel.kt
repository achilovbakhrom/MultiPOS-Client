package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.viewmodel

import android.databinding.ObservableField

class EstablishmentItemViewModel(val text: String) {

    var establishmentName: ObservableField<String>?=null
    var isClicked = ObservableField<Boolean>(false)

    init {
        establishmentName = ObservableField(text)
    }
}