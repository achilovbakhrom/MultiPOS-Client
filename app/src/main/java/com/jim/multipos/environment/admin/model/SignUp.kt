package com.jim.multipos.environment.admin.model

import java.io.Serializable

data class SignUp(val mail: String, val password: String,
                  val first_name: String, val last_name: String):Serializable
