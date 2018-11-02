package com.jim.multipos.core

interface BaseActions<T> {
    fun onItemClick(item: T?, position: Int)
    fun onItemSelected(items: List<T>?, position: Int)
    fun onItemLongClick(item: T?, position: Int)
}