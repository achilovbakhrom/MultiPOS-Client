package com.jim.multipos.environment.admin.ui.mainpage.fragments.productclass

import android.databinding.ObservableField

class ProductClassItemViewModel(val text: String) {

    var productClassName: ObservableField<String>?=null
    val isClicked = ObservableField<Boolean>(false)

    init {
        productClassName = ObservableField(text)
    }
}