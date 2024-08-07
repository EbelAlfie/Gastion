package com.example.gastion.data.model.core

import com.google.gson.annotations.SerializedName

data class BaseResponseWrapper <out response> (
  @SerializedName("data") val data: response?,
  @SerializedName("error") val error: String?
)