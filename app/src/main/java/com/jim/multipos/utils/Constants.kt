package com.jim.multipos.utils

const val BASE_URL = "http://192.168.0.107:8081"
//const val BASE_URL = "https://jsonplaceholder.typicode.com/"

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}


enum class FragmentCommunicationOperations(val operation: String) {
    ITEM_SELECTED("ITEM_SELECTED"),
    REFRESH_LIST("REFRESH_LIST"),
    ADD_NEW_ITEM("ADD_NEW_ITEM"),
    CANCEL("CANCEL"),
    ITEM_DELETED("ITEM_DELETED"),
    ITEM_EDITED("ITEM_EDITED")
}