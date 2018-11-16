package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CompanyDTO(
        @SerializedName("company") var company: Company? = null,
        @SerializedName("company_contact_persons") var companyContactPersons: MutableList<CompanyContactPerson>? = mutableListOf(),
        @SerializedName("requisites") var requisites: MutableList<Requisite>? = mutableListOf()) : Serializable