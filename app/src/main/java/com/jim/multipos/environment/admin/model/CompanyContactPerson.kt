package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CompanyContactPerson(@SerializedName("first_name") var firstName: String?,
                                @SerializedName("last_name") var lastName: String?,
                                @SerializedName("position_id") var positionId: String?,
                                @SerializedName("gender") var gender: Int?,
                                @SerializedName("marital_status") var maritalStatus: Int?,
                                @SerializedName("nationality") var nationality: String?,
                                @SerializedName("image_url") var imageUrl: String?,
                                @SerializedName("languages") var languages: List<String>?,
                                @SerializedName("work_phone_no") var work_PhoneNo: String?,
                                @SerializedName("personal_phone_no") var personalPhoneNo: String?,
                                @SerializedName("mobile_phone_no") var mobilePhoneNo: String?,
                                @SerializedName("email") var email: String?,
                                @SerializedName("birth_date") var birthDate: String?,
                                @SerializedName("company_id") var companyId: String?
) : Serializable
