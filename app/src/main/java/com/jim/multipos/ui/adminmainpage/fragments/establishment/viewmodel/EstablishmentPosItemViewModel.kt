package com.jim.multipos.ui.adminmainpage.fragments.establishment.viewmodel

import android.databinding.ObservableField

class EstablishmentPosItemViewModel(val text: String) {

    var establishmentName: ObservableField<String>?=null
    var isClicked = ObservableField<Boolean>(false)

    init {
        establishmentName = ObservableField(text)
    }
}