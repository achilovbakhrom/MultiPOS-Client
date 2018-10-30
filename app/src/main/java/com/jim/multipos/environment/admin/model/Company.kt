package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(@SerializedName("name") var name: String,
                   @SerializedName("occupation") var occupation: String?,
                   @SerializedName("logo_url") var logoUrl: String? = null,
                   @SerializedName("business_type") var businessType: Int = 0,
                   @SerializedName("tenant_id") var tenantId: String = "",
                   @SerializedName("address_information") var addressInformation: AddressInformation?,
                   @SerializedName("contact_data") var contactData: List<ContactData>)  :Serializable