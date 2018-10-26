package com.jim.multipos.environment.admin.model

import java.io.Serializable

data class Company(
        var id: String = "",
        var name: String = "",
        var description: String? = null,
        var active: Boolean = false
        ) :Serializable