package com.jim.multipos.environment.admin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignIn(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("expires_in") val expiresIn: Int,
        @SerializedName("refresh_expires_in") val refreshExpiresIn: String,
        @SerializedName("refresh_token") val refreshToken: String,
        @SerializedName("token_type") val tokenType: String,
        @SerializedName("session_state") val sessionState: String,
        @SerializedName("expires_at") val expiresAt: Long): Serializable

data class SignInRequest(
        @SerializedName("username") val  username: String,
        @SerializedName("password") val password: String,
        @SerializedName("grant_type") val grantType: String,
        @SerializedName("client_id") val clientId: String,
        @SerializedName("client_secret") val clientSecret: String
): Serializable