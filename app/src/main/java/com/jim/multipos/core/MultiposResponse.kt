package com.jim.multipos.core

import com.jim.multipos.utils.Status
import java.io.Serializable

data class MultiposResponse<T: Serializable>(val status: Status, val data: T?, val error: Throwable?)