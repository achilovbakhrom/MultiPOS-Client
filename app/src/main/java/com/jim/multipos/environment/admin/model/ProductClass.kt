package com.jim.multipos.environment.admin.model

import java.io.Serializable

data class ProductClass(
        var id: String,
        var name: String,
        var description: String?,
        var active: Boolean
        ) : Serializable