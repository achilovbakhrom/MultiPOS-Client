package com.jim.multipos.environment.admin.ui.dashboard.viewmodel

import android.databinding.ObservableField


class DashboardOrderItemViewModel(var text: String){

    var orderName: ObservableField<String>?=null

    init {
        orderName = ObservableField(text)
    }
}