package com.jim.multipos.core

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.notify(fragment: Fragment, action: String? = null, data: Any? = null) {
    val fragments = this.supportFragmentManager.fragments
    for (fr in fragments) {
        if (fr::class.java.simpleName == fragment::class.java.simpleName) {
            if (fr is Notifiable) {
                fr.notify(action, data)
            } else {
                throw Exception(message = "${fr::class.java.simpleName} does not support Notifiable protocol")
            }
            break
        }
    }
}

fun AppCompatActivity.notify(tag: String, action: String? = null, data: Any? = null) {
    var fragment = this.supportFragmentManager.findFragmentByTag(tag)
    if (fragment != null) {
        if (fragment is Notifiable) {
            fragment.notify(action, data)
        } else {
            throw Exception(message = "${fragment::class.java.simpleName} does not support Notifiable protocol")
        }
    }
}