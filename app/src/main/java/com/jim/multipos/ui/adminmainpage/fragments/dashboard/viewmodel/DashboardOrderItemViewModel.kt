package com.jim.multipos.ui.adminmainpage.fragments.dashboard.viewmodel

import android.databinding.ObservableField


class DashboardOrderItemViewModel(var text: String){

    var orderName: ObservableField<String>?=null

    init {
        orderName = ObservableField(text)
    }
}