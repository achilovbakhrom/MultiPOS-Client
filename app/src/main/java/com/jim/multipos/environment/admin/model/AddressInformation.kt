package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AddressInformation(
        @SerializedName("type") var type: String?,
        @SerializedName("street_address_1") var streetAddress1: String?,
        @SerializedName("street_address_2") var streetAddress2: String? = null,
        @SerializedName("city") var city: String? = null,
        @SerializedName("country") var country: String? = null,
        @SerializedName("state") var state: String? = null,
        @SerializedName("post_code") var postCode: String? = null
) : Serializable