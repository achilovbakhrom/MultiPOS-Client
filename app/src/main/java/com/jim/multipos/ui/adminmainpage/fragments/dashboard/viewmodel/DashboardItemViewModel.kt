package com.jim.multipos.ui.adminmainpage.fragments.dashboard.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

class DashboardItemViewModel(var text: String) {
    val isClicked = ObservableBoolean(false)

    var title: ObservableField<String>?=null

    init {
        title = ObservableField(text)
    }
}