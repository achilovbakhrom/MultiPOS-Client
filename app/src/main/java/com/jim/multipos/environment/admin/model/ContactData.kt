package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContactData(
        @SerializedName("type")var type: Int = 0,
        @SerializedName("data") var data: String = ""
): Serializable