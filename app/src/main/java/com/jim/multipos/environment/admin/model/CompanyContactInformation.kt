package com.jim.multipos.environment.admin.model

import java.io.Serializable

class CompanyContactInformation(var list: MutableList<CompanyContactPerson>,
                                var onNextAction: Boolean = false): Serializable