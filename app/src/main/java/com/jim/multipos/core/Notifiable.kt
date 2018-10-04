package com.jim.multipos.core

interface Notifiable {
    fun notify(action: String? = null, data: Any? = null)
}