package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Requisite(@SerializedName("requisite_name") var requisiteName: String?,
                     @SerializedName("requisite_items") var requisiteItems: MutableList<RequisiteItem>?,
                     @SerializedName("subject_type") var subjectType: Int?,
                     @SerializedName("requisite_description") var requisiteDescription: String?) : Serializable

data class RequisiteItem(@SerializedName("requisite_item_name") var requisiteItemName: String?,
                         @SerializedName("requisite_item_value") var requisiteItemValue: String?) : Serializable