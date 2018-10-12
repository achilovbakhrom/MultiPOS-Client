package com.jim.multipos.environment.admin.model

import java.io.Serializable

data class SignIn(val access_token: String,
                  val expires_in: Int,
                  val refresh_expires_in: String,
                  val refresh_token:String,
                  val token_type:String,
                  val session_state:String,
                  val expires_at:Int) :Serializable