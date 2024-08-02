package com.example.gastion.data.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
  @SerializedName("username") val userName: String,
  @SerializedName("password") val password: String
)

data class UserResponse(
  @SerializedName("token") val token: String?
)