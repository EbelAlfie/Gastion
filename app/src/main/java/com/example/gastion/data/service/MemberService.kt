package com.example.gastion.data.service

import com.example.gastion.data.model.UserRequest
import com.example.gastion.data.model.UserResponse
import com.example.gastion.data.model.core.BaseResponseWrapper
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberService {
  @POST("{url}")
  suspend fun login(
    @Body request: UserRequest
  ): BaseResponseWrapper<UserResponse>
}