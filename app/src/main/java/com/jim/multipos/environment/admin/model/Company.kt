package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company(var localPhotoPath: String? = null,
                   @SerializedName("name") var name: String = "",
                   @SerializedName("occupation") var occupation: String? = null,
                   @SerializedName("logo_url") var logoUrl: String? = null,
                   @SerializedName("business_type") var businessType: Int = 0,
                   @SerializedName("tenant_id") var tenantId: String = "",
                   @SerializedName("address_information") var addressInformation: AddressInformation? = null,
                   @SerializedName("contact_data") var contactData: List<ContactData>? = null,
                   @SerializedName("description") var description: String? = null)  :Serializable