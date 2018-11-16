package com.jim.multipos.environment.admin.model

import java.io.Serializable

data class AboutInformation(var imagePath: String? = null,
                            var companyName: String = "",
                            var companyOccupation: String = "",
                            var contactData: List<ContactData>? = null,
                            var description: String? = null,
                            var onNextAction: Boolean = false,
                            var editMode: Boolean = false) : Serializable