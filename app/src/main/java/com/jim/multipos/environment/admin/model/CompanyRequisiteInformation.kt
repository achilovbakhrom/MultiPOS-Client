package com.jim.multipos.environment.admin.model

import java.io.Serializable

class CompanyRequisiteInformation(var list: MutableList<Requisite>,
                                  var onNext: Boolean = false) : Serializable