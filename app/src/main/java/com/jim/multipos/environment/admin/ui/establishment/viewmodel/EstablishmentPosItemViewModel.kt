package com.jim.multipos.environment.admin.ui.establishment.viewmodel

import android.databinding.ObservableField

class EstablishmentPosItemViewModel(val text: String) {

    var establishmentName: ObservableField<String>?=null
    var isClicked = ObservableField<Boolean>(false)

    init {
        establishmentName = ObservableField(text)
    }
}