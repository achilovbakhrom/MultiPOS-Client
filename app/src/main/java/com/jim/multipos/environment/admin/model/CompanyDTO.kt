package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CompanyDTO(@SerializedName("company") var company: Company?,
                            @SerializedName("company_contact_persons") var companyContactPersons: List<CompanyContactPerson>?,
                            @SerializedName("requisites") var requisites: List<Requisite>?): Serializable