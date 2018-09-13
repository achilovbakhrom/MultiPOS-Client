package com.jim.multipos.ui.adminmainpage.fragments.products

import android.databinding.ObservableField

class ProductsItemViewModel(val text: String) {

    var productName: ObservableField<String>?=null
    val isClicked = ObservableField<Boolean>(false)

    init {
        productName = ObservableField(text)
    }
}