package com.jim.multipos.core

import java.io.Serializable

data class MultiposResponseSingle<T: Serializable>(val status: String?, val data: T?, val code: Int?, val message: String?)
data class MultiposResponseList<T:Serializable>(val status: String?, val data: List<T>?, val code: Int?, val message: String?)