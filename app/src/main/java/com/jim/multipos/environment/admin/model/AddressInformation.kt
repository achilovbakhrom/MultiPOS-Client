package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AddressInformation(
        @SerializedName("type") var type: String = AddressType.OFFICE.value(),
        @SerializedName("street_address_1") var streetAddress1: String? = null,
        @SerializedName("street_address_2") var streetAddress2: String? = null,
        @SerializedName("city") var city: String? = null,
        @SerializedName("country") var country: String? = null,
        @SerializedName("state") var state: String? = null,
        @SerializedName("post_code") var postCode: String? = null,
        @SerializedName("description") var description: String? = null
) : Serializable

enum class AddressType(val type: String) {
    HOME("home"),
    OFFICE("office");
    fun value() = type
}
