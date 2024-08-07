package com.example.gastion.data

import com.example.gastion.data.model.UserRequest
import com.example.gastion.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

  suspend fun login(loginRequest: UserRequest): Flow<UserResponse>
}