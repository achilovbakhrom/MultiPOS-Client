package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CompanyDTO(
        @SerializedName("company") var company: Company? = null,
        @SerializedName("company_contact_persons") var companyContactPersons: List<CompanyContactPerson>? = listOf(),
        @SerializedName("requisites") var requisites: List<Requisite>? = listOf()) : Serializable